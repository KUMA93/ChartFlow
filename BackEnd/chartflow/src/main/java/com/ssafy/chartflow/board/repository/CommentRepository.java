package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {


}
