package com.example.security.model;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String explanation; // 추가 설명
    private Long view;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    private Timestamp createDate;
    private Timestamp studyDate;


    public void update(Note note) {
        this.title = note.getTitle();
        this.explanation = note.getExplanation();
    }

    public void viewCount() { this.view += 1;}
}
