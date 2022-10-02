package com.example.security.cotroller;

import com.example.security.config.service.NoteService;
import com.example.security.config.service.WordService;
import com.example.security.model.Note;
import com.example.security.model.Word;
import com.example.security.repository.UserRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
        Note note = noteService.read(id);
        if (note != null) {
            // 노트 아이디의 단어 리스트를 검색
            List<Word> wordList = wordService.words(note.getId());
            // 모델에 단어장과 단어 리스트를 반환
            model.addAttribute("note",note);
            model.addAttribute("wordList",wordList);
        }
        // 뷰 페이지에 전달
        return "note/details";
    }

    @GetMapping("/note/{id}/study")
    public String study(@PathVariable Long id, Model model) {
        Note note = noteService.read(id);
        List<Word> wordList = wordService.words(note.getId());
        Collections.shuffle(wordList);
        model.addAttribute("note", note);
        model.addAttribute("wordList", wordList);

        return "note/study";
    }

}
