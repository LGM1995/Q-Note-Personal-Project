package com.example.qnote.repository;

import com.example.qnote.model.Word;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(value =
        "SELECT * " +
            "FROM word " +
            "WHERE note_id = :noteId",
        nativeQuery = true)
    List<Word> findByNoteId(@Param("noteId") Long noteId);

    @Query(value =
        "SELECT * " +
            "FROM word " +
            "WHERE name LIKE %:name%",
        nativeQuery = true)
    List<Word> findByNames(@Param("name") String title);
    // 특정 이름을 포함한 단어 모두 찾기

    @Query(value =
        "SELECT * " +
            "FROM word " +
            "WHERE mean LIKE %:mean%",
        nativeQuery = true)
    List<Word> findByMeans(@Param("mean") String mean);
    // 특정 의미를 포함한 단어 모두 찾기
}
