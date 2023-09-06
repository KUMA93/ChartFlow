package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.ReComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReCommentRepository extends JpaRepository<ReComments, Long> {

    public List<ReComments> findByCommentId(long commentId);
}
