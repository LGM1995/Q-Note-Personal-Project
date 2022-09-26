package com.example.security.api;

import com.example.security.config.service.NoteService;
import com.example.security.config.service.WordService;
import com.example.security.model.Note;
import com.example.security.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordApiController {

    @Autowired
    private WordService wordService;

    // 단어장 생성
    @PostMapping("/api/quizlet/{noteId}/word")
    public ResponseEntity<Word> create(@PathVariable Integer noteId,
        @RequestBody Word word) {
        // 서비스에게 위임
        Word cword = wordService.create(noteId, word);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(cword);
    }

    // 단어장 삭제
    @DeleteMapping("/api/quizlet/word/{id}")
    public ResponseEntity<Word> delete(@PathVariable Integer id) {
        Word word = wordService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(word);
    }

}
