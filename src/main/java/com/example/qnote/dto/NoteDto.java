package com.example.qnote.dto;

import com.example.qnote.model.Note;
import com.example.qnote.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NoteDto {

    private Long id;
    private String title;
    private String explanation; // 추가 설명
    private Long view;
    private UserDto user;
    private Timestamp createDate;
    private Timestamp studyDate;

    public static NoteDto fromEntity (Note note) {
        return new NoteDto(
            note.getId(),
            note.getTitle(),
            note.getExplanation(),
            note.getView(),
            UserDto.fromEntity(note.getUser()),
            note.getCreateDate(),
            note.getStudyDate());
    }
}
