package com.example.qnote.api;

import com.example.qnote.config.service.NoteService;
import com.example.qnote.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteApiController {
    @Autowired
    private NoteService noteService;

    // 단어장 생성
    @PostMapping("/api/{userId}/note")
    public ResponseEntity<Note> create(@PathVariable Long userId,
        @RequestBody Note note) {
        // 서비스에게 위임
        Note cnote = noteService.create(userId, note);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(cnote);
    }

    // 단어장 삭제
    @DeleteMapping("/api/note/{id}")
    public ResponseEntity<Note> delete(@PathVariable Long id) {
        Note note = noteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(note);
    }
}
