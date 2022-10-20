package com.example.qnote.cotroller;

import com.example.qnote.config.service.NoteService;
import com.example.qnote.config.service.WordService;
import com.example.qnote.dto.NoteDto;
import com.example.qnote.dto.WordDto;
import com.example.qnote.model.Note;
import com.example.qnote.model.Word;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoteController {

    private final NoteService noteService;

    private final WordService wordService;

    public NoteController(NoteService noteService, WordService wordService) {
        this.noteService = noteService;
        this.wordService = wordService;
    }

    @GetMapping("/note/{id}")
    public String details(@PathVariable Long id, Model model) {
        // 노트 아이디의 단어장을 검색
        NoteDto noteDto = noteService.read(id);
        if (noteDto != null) {
            // 노트 아이디의 단어 리스트를 검색
            List<WordDto> wordDtos = wordService.words(noteDto.getId());
            // 모델에 단어장과 단어 리스트를 반환
            model.addAttribute("note",noteDto);
            model.addAttribute("wordList",wordDtos);
        }
        // 뷰 페이지에 전달
        return "note/details";
    }

    @GetMapping("/note/{id}/study")
    public String study(@PathVariable Long id, Model model) {
        NoteDto noteDto = noteService.read(id);
        List<WordDto> wordDtos = wordService.words(noteDto.getId());
        Collections.shuffle(wordDtos);
        model.addAttribute("note", noteDto);
        model.addAttribute("wordList", wordDtos);

        return "note/study";
    }

}
