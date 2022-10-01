package com.example.security.api;

import com.example.security.config.service.NoteService;
import com.example.security.config.service.WordService;
import com.example.security.model.Note;
import com.example.security.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class WordApiController {

    @Autowired
    private WordService wordService;

    @Autowired
    private NoteService noteService;

    // 단어장 생성
    @PostMapping("/api/Q-Note/{noteId}/word")
    public ResponseEntity<Word> create(@PathVariable Long noteId,
        @RequestBody Word word) {
        // 서비스에게 위임
        Word cword = wordService.create(noteId, word);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(cword);
    }

    // 단어장 삭제
    @DeleteMapping("/api/Q-Note/word/{id}")
    public ResponseEntity<Word> delete(@PathVariable Long id) {
        Word word = wordService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(word);
    }

    // 단어 시험
    @PostMapping("api/Q-Note/{noteId}/test")
    public ResponseEntity<List<Word>> test(@PathVariable Long noteId,
                                           @RequestBody List<Word> words) {
        System.out.println(words.stream().toArray().toString()
        );
        // 틀린 문제들이 반환된다.
        List<Word> wordList = wordService.CountUp(words);
        noteService.viewCountUp(noteId);
        return ResponseEntity.status(HttpStatus.OK).body(wordList);
    }

}
