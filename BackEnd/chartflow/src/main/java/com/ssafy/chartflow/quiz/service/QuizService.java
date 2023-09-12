package com.ssafy.chartflow.quiz.service;

import com.ssafy.chartflow.quiz.entity.Quiz;
import com.ssafy.chartflow.quiz.entity.QuizChoices;
import com.ssafy.chartflow.quiz.entity.UserQuiz;
import com.ssafy.chartflow.quiz.repository.QuizChoicesRepository;
import com.ssafy.chartflow.quiz.repository.QuizRepository;
import com.ssafy.chartflow.quiz.repository.UserQuizRepository;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizChoicesRepository quizChoicesRepository;
    private final UserQuizRepository userQuizRepository;
    private final UserRepository userRepository;
    private final int QuizCount = 3;  // 가져올 퀴즈의 개수

    // 오늘의 퀴즈 id리턴
    public Long getTodayQuizId() {
        long id = -1L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date1 = LocalDate.parse("20230906", formatter);
        LocalDate date2 = LocalDate.now();
        id = DAYS.between(date1, date2) * QuizCount;
        return id;
    }

    // 오늘의 퀴즈 리스트 리턴
    public List<Quiz> getTodayQuizzes() {
        return quizRepository.findFirst3ByIdGreaterThanOrderByIdAsc(getTodayQuizId());
    }


    public String getAnswerByQuizId(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz != null) {
            return quiz.getAnswer();
        } else {
            // 원하는 quiz_id가 존재하지 않을 때 처리
            return "해당하는 퀴즈가 없습니다.";
        }
    }

    public QuizChoices[] getChoices(Long quizId) {
        QuizChoices[] choices = quizChoicesRepository.findByQuizId(quizId);
        if (choices.length != 0)
            return choices;
        else
            return null;
    }

    public boolean quizResult(Long userId, Long quizId, String choice) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        String correct_content = quiz.getAnswer();  // 정답

        // 유저의 퀴즈 게임결과 저장
        UserQuiz userQuiz = UserQuiz.builder()
                .quiz(quiz)
                .user(user)
                .choice(choice)
                .build();
        userQuizRepository.save(userQuiz);

        if (choice.equals(correct_content))
            return true;
        else
            return false;

    }

}
