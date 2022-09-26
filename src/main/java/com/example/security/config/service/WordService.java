package com.example.security.config.service;

import com.example.security.model.Note;
import com.example.security.model.User;
import com.example.security.model.Word;
import com.example.security.repository.NoteRepository;
import com.example.security.repository.UserRepository;
import com.example.security.repository.WordRepository;
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

    public List<Word> words(Integer noteId) {
        return wordRepository.findByNoteId(noteId);
    }

    public List<Word> shuffle(Integer noteId) {
        List<Word> wordlist = wordRepository.findByNoteId(noteId);
        Collections.shuffle(wordlist);
        return wordlist;
    }

    @Transactional
    public Word create(Integer noteId, Word word) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note != null) {
            Word cword = Word.builder()
                .name(word.getName())
                .mean(word.getMean())
                .fail_count(0)
                .success_count(0)
                .studyDate(null)
                .note(note)
                .build();
            wordRepository.save(cword);
        }
        return null;
    }

    @Transactional
    public Word update(Integer id, Word word) {
        Word target = (wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 수정 실패!")));

        target.update(word);

        return wordRepository.save(target);
    }

    @Transactional
    public Word read(Integer id) {
        return wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 로드 실패!"));
    }

    @Transactional
    public Word delete(Integer id) {
        Word word = wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 삭제 실패!"));
        wordRepository.delete(word);
        return word;
    }
}