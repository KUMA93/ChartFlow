package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    public Comments findCommentByCommentId(long commentId);
    public List<Comments> findByArticleId(long articleId);

}
