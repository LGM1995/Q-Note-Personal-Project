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

    @Autowired
    private NoteService noteService;

    @Autowired
    private WordService wordService;

    @GetMapping("/quizlet/note/{id}")
    public String details(@PathVariable Integer id, Model model) {
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

    @GetMapping("/quizlet/note/{id}/study")
    public String study(@PathVariable Integer noteId, Model model) {
        List<Word> wordList = wordService.shuffle(noteId);
        Note note = noteService.read(noteId);
        List<Word> words = null;
        for (int i = 0; i < 10; i++) {
            words.add(wordList.get(0));
        }
        model.addAttribute("words",words);
        model.addAttribute("note", note);

        return "note/study";
    }

}
