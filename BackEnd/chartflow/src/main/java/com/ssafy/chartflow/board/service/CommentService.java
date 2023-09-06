package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ResponseCommentDto;
import com.ssafy.chartflow.board.dto.response.ResponseReCommentDto;
import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.entity.Comments;
import com.ssafy.chartflow.board.entity.ReComments;
import com.ssafy.chartflow.board.repository.ArticleRepository;
import com.ssafy.chartflow.board.repository.CommentRepository;
import com.ssafy.chartflow.board.repository.ReCommentRepository;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CommentService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ReCommentRepository reCommentRepository;

    public List<ResponseCommentDto> searchAll(long articleId) {

        List<Comments> commentList = commentRepository.findByArticleId(articleId);
        List<ResponseCommentDto> returnComments = new ArrayList<>();

        for (Comments comments : commentList) {
            List<ReComments> reCommentList = reCommentRepository.findByCommentId(comments.getCommentId());
            List<ResponseReCommentDto> responseCommentDtos = new ArrayList<>();
            for (ReComments reComments : reCommentList) {
                ResponseReCommentDto responseReCommentDto = ResponseReCommentDto.builder()
                        .reCommentId(reComments.getReCommentId())
                        .commentId(reComments.getReCommentId())
                        .userId(reComments.getUser().getUserId())
                        .content(reComments.getContent())
                        .registerTime(reComments.getRegisterTime())
                        .build();

                responseCommentDtos.add(responseReCommentDto);
            }
            ResponseCommentDto responseCommentDto = ResponseCommentDto.builder()
                    .commentId(comments.getCommentId())
                    .userId(comments.getUser().getUserId())
                    .content(comments.getContent())
                    .registerTime(comments.getRegisterTime())
                    .reComments(responseCommentDtos)
                    .build();

            returnComments.add(responseCommentDto);
        }

        return returnComments;
    }

    public void write(long articleId, long userId, String content) {
        log.info("Comment Service - 댓글 작성");

        Comments comments = Comments.builder()
                .article(articleRepository.findArticleByArticleId(articleId))
                .user(userRepository.findUserByUserId(userId))
                .content(content)
                .registerTime(LocalDateTime.now())
                .build();
        commentRepository.save(comments);
        log.info("등록된 Comment : " + comments);
        return;
    }

    public void writeReComment(long commentId, long userId, String content) {
        log.info("Comment Service - 대댓글 작성");

        ReComments reComments = ReComments.builder()
                .comment(commentRepository.findCommentByCommentId(commentId))
                .user(userRepository.findUserByUserId(userId))
                .content(content)
                .registerTime(LocalDateTime.now())
                .build();
        reCommentRepository.save(reComments);
        log.info("등록된 reComment : " + reComments);
        return;
    }
}
