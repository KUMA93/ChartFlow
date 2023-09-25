package com.ssafy.chartflow.quiz.repository;

import com.ssafy.chartflow.quiz.entity.Quiz;
import com.ssafy.chartflow.quiz.entity.QuizChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> { //JpaRepository<Entity클래스, PK타입>

    Quiz findById(long id);
    List<Quiz> findAll();
    List<Quiz> findFirst3ByIdGreaterThanOrderByIdAsc(Long id);
}
