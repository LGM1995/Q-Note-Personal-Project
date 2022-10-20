package com.example.qnote.dto;

import com.example.qnote.model.Note;
import com.example.qnote.model.Word;
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
@Getter
@Setter
public class WordDto {

    private Long id;
    private String name;
    private String mean;
    private Long fail_count;
    private Long success_count;
    private NoteDto note;
    private Timestamp createDate;
    private Timestamp studyDate;

    public static WordDto fromEntity (Word word) {
        return new WordDto(
            word.getId(),
            word.getName(),
            word.getMean(),
            word.getFail_count(),
            word.getSuccess_count(),
            NoteDto.fromEntity(word.getNote()),
            word.getCreateDate(),
            word.getStudyDate());
    }
}
