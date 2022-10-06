package com.example.qnote.config.service;

import com.example.qnote.model.Note;
import com.example.qnote.model.User;
import com.example.qnote.repository.NoteRepository;
import com.example.qnote.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public NoteService(UserRepository userRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    public List<Note> notes(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    @Transactional
    public Note create(Long userId, Note note) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Note cnote = Note.builder()
                .title(note.getTitle())
                .view(0L)
                .explanation(note.getExplanation())
                .user(user)
                .studyDate(null)
                .build();
            noteRepository.save(cnote);
        }
        return null;
    }

    @Transactional
    public Note update(Long id, Note note) {
        Note target = (noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 수정 실패!")));

        target.update(note);

        return noteRepository.save(target);
    }

    @Transactional
    public Note read(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 로드 실패!"));
    }

    @Transactional
    public Note delete(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 삭제 실패!"));
        noteRepository.delete(note);
        return note;
    }

    @Transactional
    public Note viewCountUp(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 로드 실패!"));
        note.viewCount();
        return note;
    }
}


