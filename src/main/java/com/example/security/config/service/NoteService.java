package com.example.security.config.service;

import com.example.security.model.Note;
import com.example.security.model.User;
import com.example.security.repository.NoteRepository;
import com.example.security.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NoteRepository noteRepository;

    public List<Note> notes(Integer userId) {
        return noteRepository.findByUserId(userId);
    }

    @Transactional
    public Note create(Integer userId, Note note) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Note cnote = Note.builder()
                .title(note.getTitle())
                .view(0)
                .explanation(note.getExplanation())
                .user(user)
                .studyDate(null)
                .build();
            noteRepository.save(cnote);
        }
        return null;
    }

    @Transactional
    public Note update(Integer id, Note note) {
        Note target = (noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 수정 실패!")));

        target.update(note);

        return noteRepository.save(target);
    }

    @Transactional
    public Note read(Integer id) {
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 로드 실패!"));
    }

    @Transactional
    public Note delete(Integer id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 삭제 실패!"));
        noteRepository.delete(note);
        return note;
    }
}


