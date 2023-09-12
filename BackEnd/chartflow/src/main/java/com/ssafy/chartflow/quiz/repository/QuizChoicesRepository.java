package com.ssafy.chartflow.quiz.repository;

import com.ssafy.chartflow.quiz.entity.QuizChoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizChoicesRepository extends JpaRepository<QuizChoices, Long> {
    QuizChoices[] findByQuizId(Long quizId);
}
