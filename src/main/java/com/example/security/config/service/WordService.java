package com.example.security.config.service;

import com.example.security.model.Note;
import com.example.security.model.User;
import com.example.security.model.Word;
import com.example.security.repository.NoteRepository;
import com.example.security.repository.UserRepository;
import com.example.security.repository.WordRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    WordRepository wordRepository;

    public List<Word> words(Long noteId) {
        return wordRepository.findByNoteId(noteId);
    }

    public List<Word> shuffle(Long noteId) {
        List<Word> wordlist = wordRepository.findByNoteId(noteId);
        Collections.shuffle(wordlist);
        return wordlist;
    }

    @Transactional
    public Word create(Long noteId, Word word) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note != null) {
            Word cword = Word.builder()
                .name(word.getName())
                .mean(word.getMean())
                .fail_count(0L)
                .success_count(0L)
                .studyDate(null)
                .note(note)
                .build();
            wordRepository.save(cword);
        }
        return null;
    }

    @Transactional
    public List<Word> CountUp(List<Word> words) {
        List<Word> failList = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            Word target = (wordRepository.findById(word.getId()).orElseThrow(() -> new IllegalArgumentException("단어 찾기 실패!")));
            if (target.getMean().equals(word.getMean())) {
                target.success();
            } else {
                // 오답 체크를 위해 틀린 문제를 리턴할 것
                failList.add(target);
                target.fail();
            }
            wordRepository.save(target);
        }
        return failList;
    }

    @Transactional
    public Word update(Long id, Word word) {
        Word target = (wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 수정 실패!")));

        target.update(word);

        return wordRepository.save(target);
    }

    @Transactional
    public Word read(Long id) {
        return wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 로드 실패!"));
    }

    @Transactional
    public Word delete(Long id) {
        Word word = wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 삭제 실패!"));
        wordRepository.delete(word);
        return word;
    }
}