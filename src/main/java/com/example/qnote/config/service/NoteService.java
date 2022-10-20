package com.example.qnote.config.service;

import com.example.qnote.dto.NoteDto;
import com.example.qnote.dto.WordDto;
import com.example.qnote.model.Note;
import com.example.qnote.model.User;
import com.example.qnote.repository.NoteRepository;
import com.example.qnote.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<NoteDto> notes(Long userId) {
        return noteRepository.findByUserId(userId).stream().map(note -> NoteDto.fromEntity(note)).collect(Collectors.toList());
    }

    @Transactional
    public NoteDto create(Long userId, NoteDto noteDto) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Note cnote = Note.builder()
                .title(noteDto.getTitle())
                .view(0L)
                .explanation(noteDto.getExplanation())
                .user(user)
                .studyDate(null)
                .build();
            noteRepository.save(cnote);
        }
        return null;
    }

    @Transactional
    public NoteDto update(Long id, Note note) {
        Note target = (noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 수정 실패!")));

        target.update(note);

        return NoteDto.fromEntity(noteRepository.save(target));
    }

    @Transactional
    public NoteDto read(Long id) {
        return NoteDto.fromEntity(noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 로드 실패!")));
    }

    @Transactional
    public NoteDto delete(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 삭제 실패!"));
        noteRepository.delete(note);
        return NoteDto.fromEntity(note);
    }

    @Transactional
    public NoteDto viewCountUp(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("단어장 로드 실패!"));
        note.viewCount();
        return NoteDto.fromEntity(note);
    }
}


