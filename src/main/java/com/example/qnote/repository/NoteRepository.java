package com.example.qnote.repository;

import com.example.qnote.model.Note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<Note, Long> {

    public Note findByTitle(String title);
    // Jpa 네임 함수에 의하여 해당 테이블의 파라미터 값으로 데이터를 자동으로 쿼리한다.


    @Query(value =
        "SELECT * " +
            "FROM note " +
            "WHERE user_id = :userId",
        nativeQuery = true)
    List<Note> findByUserId(@Param("userId") Long userId);

    @Query(value =
        "SELECT * " +
            "FROM note " +
            "WHERE title LIKE %:title%",
        nativeQuery = true)
    List<Note> findByTitles(@Param("title") String title);
    // 특정 제목을 포함한 타이틀 모두 찾기

    @Query(value =
        "SELECT * " +
            "FROM note " +
            "WHERE title LIKE %:explanation%",
        nativeQuery = true)
    List<Note> findByExplanations(@Param("explanation") String explanation);
    // 특정 제목을 포함한 타이틀 모두 찾기
}

