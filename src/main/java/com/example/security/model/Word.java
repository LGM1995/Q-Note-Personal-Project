package com.example.security.model;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "word")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mean; // 추가 설명
    private Long fail_count;
    private Long success_count;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "note_id")
    private Note note;
    @CreationTimestamp
    private Timestamp createDate;
    private Timestamp studyDate;

    public void update(Word word) {
        this.name = word.getName();
        this.mean = word.getMean();
    }
    public void success() { this.success_count += 1;}

    public void fail() {
        this.fail_count += 1;
    }
}

