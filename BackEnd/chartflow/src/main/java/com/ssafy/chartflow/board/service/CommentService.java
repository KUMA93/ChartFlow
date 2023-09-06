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

import javax.xml.stream.events.Comment;
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
                        .cancel(reComments.getCancel())
                        .modify(reComments.getModify())
                        .build();

                responseCommentDtos.add(responseReCommentDto);
            }
            ResponseCommentDto responseCommentDto = ResponseCommentDto.builder()
                    .commentId(comments.getCommentId())
                    .userId(comments.getUser().getUserId())
                    .content(comments.getContent())
                    .registerTime(comments.getRegisterTime())
                    .cancel(comments.getCancel())
                    .modify(comments.getModify())
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
                .cancel(0)
                .modify(0)
                .build();
        commentRepository.save(comments);
        log.info("등록된 Comment : " + comments.getContent());
        return;
    }

    public void writeReComment(long commentId, long userId, String content) {
        log.info("Comment Service - 대댓글 작성");

        ReComments reComment = ReComments.builder()
                .comment(commentRepository.findCommentByCommentId(commentId))
                .user(userRepository.findUserByUserId(userId))
                .content(content)
                .registerTime(LocalDateTime.now())
                .cancel(0)
                .modify(0)
                .build();
        reCommentRepository.save(reComment);
        log.info("등록된 reComment : " + reComment.getContent());
        return;
    }

    public void modifyComment(long commentId, String content) {
        log.info("Comment Service - 댓글 수정");

        Comments comment = Comments.builder()
                .commentId(commentId)
                .content(content)
                .modify(1)
                .build();

        commentRepository.save(comment);
        log.info("수정된 comment : " + comment.getContent());

        return;
    }

    public void modifyReComment(long reCommentId, String content) {
        log.info("Comment Service - 대댓글 수정");

        ReComments reComment = ReComments.builder()
                .reCommentId(reCommentId)
                .content(content)
                .modify(1)
                .build();

        reCommentRepository.save(reComment);
        log.info("수정된 reComment : " + reComment.getContent());
        return;
    }

    public void deleteComment(long commentId) {
        log.info("Comment Service - 댓글 삭제");

        Comments comment = Comments.builder()
                .cancel(1)
                .build();

        commentRepository.save(comment);
        log.info(comment.getCommentId() + " 삭제");
        return;
    }

    public void deleteReComment(long reCommentId) {
        log.info("Comment Service - 대댓글 삭제");

        ReComments reComment = ReComments.builder()
                .cancel(1)
                .build();

        reCommentRepository.save(reComment);
        log.info(reComment.getReCommentId() + " 삭제");
        return;
    }

}
