package com.ssafy.chartflow.quiz.repository;

import com.ssafy.chartflow.quiz.entity.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {
}
