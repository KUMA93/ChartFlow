package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.entity.Comments;
import com.ssafy.chartflow.board.repository.ArticleRepository;
import com.ssafy.chartflow.board.repository.CommentRepository;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CommentService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;



    public Comments write(long articleId, long userId, String content) {
        log.info("Comment Service - 댓글 작성");

        Comments comments = Comments.builder()
                .article(articleRepository.getArticleByArticleId(articleId))
                .user(userRepository.findUserByUserId(userId))
                .content(content)
                .build();

        return commentRepository.save(comments);
    }
}
