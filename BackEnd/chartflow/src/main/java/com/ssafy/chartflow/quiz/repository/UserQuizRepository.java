package com.ssafy.chartflow.quiz.repository;

import com.ssafy.chartflow.quiz.entity.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {

    UserQuiz findUserQuizByUserIdAndQuizId(Long userId, Long QuizId);
}
