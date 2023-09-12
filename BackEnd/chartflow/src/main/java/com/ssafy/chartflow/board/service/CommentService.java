package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ResponseCommentDto;
import com.ssafy.chartflow.board.dto.response.ResponseReCommentDto;
import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.entity.Comments;
import com.ssafy.chartflow.board.entity.ReComments;
import com.ssafy.chartflow.board.repository.ArticleRepository;
import com.ssafy.chartflow.board.repository.CommentRepository;
import com.ssafy.chartflow.board.repository.ReCommentRepository;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        Article article = articleRepository.findArticleById(articleId);
        List<Comments> commentList = article.getComments();
        List<ResponseCommentDto> returnComments = new ArrayList<>();

        for (Comments comments : commentList) {
            List<ReComments> reCommentList = comments.getReComments();
            List<ResponseReCommentDto> responseCommentDtos = new ArrayList<>();
            for (ReComments reComments : reCommentList) {
                ResponseReCommentDto responseReCommentDto = ResponseReCommentDto.builder()
                        .reCommentId(reComments.getId())
                        .commentId(reComments.getId())
                        .userId(reComments.getUser().getId())
                        .content(reComments.getContent())
                        .registerTime(reComments.getRegisterTime())
                        .cancel(reComments.getCancel())
                        .modify(reComments.getModify())
                        .build();

                responseCommentDtos.add(responseReCommentDto);
            }
            ResponseCommentDto responseCommentDto = ResponseCommentDto.builder()
                    .commentId(comments.getId())
                    .userId(comments.getUser().getId())
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

        User user = userRepository.findUserById(userId);
        Article article = articleRepository.findArticleById(articleId);
        Comments comments = Comments.builder()
                .content(content)
                .registerTime(LocalDateTime.now())
                .cancel(0)
                .modify(0)
                .build();
        comments.setUser(user);
        comments.setArticle(article);

        userRepository.save(user);
        articleRepository.save(article);
        commentRepository.save(comments);
        log.info("등록된 Comment : " + comments.getContent());
        return;
    }

    public void writeReComment(long commentId, long userId, String content) {
        log.info("Comment Service - 대댓글 작성");

        User user = userRepository.findUserById(userId);
        Comments comment = commentRepository.findCommentById(commentId);

        ReComments reComment = ReComments.builder()
                .content(content)
                .registerTime(LocalDateTime.now())
                .cancel(0)
                .modify(0)
                .build();
        reComment.setUser(user);
        reComment.setComment(comment);

        userRepository.save(user);
        commentRepository.save(comment);
        reCommentRepository.save(reComment);
        log.info("등록된 reComment : " + reComment.getContent());
        return;
    }

    public void modifyComment(long commentId, String content) {
        log.info("Comment Service - 댓글 수정");

        Comments comment = commentRepository.findCommentById(commentId);
        comment.setContent(content);
        comment.setModify(1);

        commentRepository.save(comment);
        log.info("수정된 comment : " + comment.getContent());

        return;
    }

    public void modifyReComment(long reCommentId, String content) {
        log.info("Comment Service - 대댓글 수정");

        ReComments reComment = reCommentRepository.findReCommentById(reCommentId);

        reComment.setModify(1);
        reComment.setContent(content);

        reCommentRepository.save(reComment);
        log.info("수정된 reComment : " + reComment.getContent());
        return;
    }

    public void deleteComment(long commentId) {
        log.info("Comment Service - 댓글 삭제");

        Comments comment = commentRepository.findCommentById(commentId);
        comment.setCancel(1);

        commentRepository.save(comment);
        log.info(comment.getId() + " 삭제");
        return;
    }

    public void deleteReComment(long reCommentId) {
        log.info("Comment Service - 대댓글 삭제");

        ReComments reComment = reCommentRepository.findReCommentById(reCommentId);
        reComment.setCancel(1);

        reCommentRepository.save(reComment);
        log.info(reComment.getId() + " 삭제");
        return;
    }

}
