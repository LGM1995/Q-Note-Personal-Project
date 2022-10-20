package com.example.qnote.api;

import com.example.qnote.config.service.NoteService;
import com.example.qnote.config.service.WordService;
import com.example.qnote.dto.WordDto;
import com.example.qnote.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class WordApiController {

    @Autowired
    private WordService wordService;

    @Autowired
    private NoteService noteService;

    // 단어장 생성
    @PostMapping("/api/{noteId}/word")
    public ResponseEntity<WordDto> create(@PathVariable Long noteId,
        @RequestBody WordDto wordDto) {
        // 서비스에게 위임
        WordDto created = wordService.create(noteId, wordDto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 단어장 삭제
    @DeleteMapping("/api/word/{id}")
    public ResponseEntity<WordDto> delete(@PathVariable Long id) {
        WordDto wordDto = wordService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(wordDto);
    }

    // 단어 수정
    @PatchMapping("/api/word/{id}")
    public ResponseEntity<WordDto> update(@PathVariable Long id, @RequestBody WordDto wordDto) {
        WordDto target = wordService.update(id, wordDto);
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    // 단어 시험
    @PostMapping("/api/{noteId}/test")
    public ResponseEntity<List<WordDto>> test(@PathVariable Long noteId,
                                           @RequestBody List<WordDto> wordDtos) {
        System.out.println(wordDtos.stream().toArray().toString()
        );
        // 틀린 문제들이 반환된다.
        List<WordDto> wordList = wordService.CountUp(wordDtos);
        noteService.viewCountUp(noteId);
        return ResponseEntity.status(HttpStatus.OK).body(wordList);
    }

}
