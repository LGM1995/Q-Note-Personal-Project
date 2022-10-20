package com.example.qnote.config.service;

import com.example.qnote.dto.WordDto;
import com.example.qnote.model.Note;
import com.example.qnote.model.Word;
import com.example.qnote.repository.NoteRepository;
import com.example.qnote.repository.WordRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    WordRepository wordRepository;

    public List<WordDto> words(Long noteId) {
        return wordRepository.findByNoteId(noteId).stream().map(word -> WordDto.fromEntity(word)).collect(Collectors.toList());
    }

    public List<WordDto> shuffle(Long noteId) {
        List<WordDto> wordlist = wordRepository.findByNoteId(noteId).stream().map(word -> WordDto.fromEntity(word)).collect(Collectors.toList());
        Collections.shuffle(wordlist);
        return wordlist;
    }

    @Transactional
    public WordDto create(Long noteId, WordDto wordDto) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note != null) {
            Word cword = Word.builder()
                .name(wordDto.getName())
                .mean(wordDto.getMean())
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
    public List<WordDto> CountUp(List<WordDto> wordDtos) {
        List<WordDto> failList = new ArrayList<>();
        for (int i = 0; i < wordDtos.size(); i++) {
            WordDto wordDto = wordDtos.get(i);
            Word target = (wordRepository.findById(wordDto.getId()).orElseThrow(() -> new IllegalArgumentException("단어 찾기 실패!")));
            if (target.getMean().equals(wordDto.getMean())) {
                target.success();
            } else {
                // 오답 체크를 위해 틀린 문제를 리턴할 것
                failList.add(WordDto.fromEntity(target));
                target.fail();
            }
            wordRepository.save(target);
        }
        return failList;
    }

    @Transactional
    public WordDto update(Long id, WordDto wordDto) {
        Word target = (wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 수정 실패!")));

        target.update(wordDto);

        Word created = wordRepository.save(target);

        return WordDto.fromEntity(created);
    }

    @Transactional
    public WordDto read(Long id) {
        Word target = wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 로드 실패!"));
        return WordDto.fromEntity(target);
    }

    @Transactional
    public WordDto delete(Long id) {
        Word target = wordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어 삭제 실패!"));
        wordRepository.delete(target);
        return WordDto.fromEntity(target);
    }
}