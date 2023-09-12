package com.ssafy.chartflow.board.repository;

import com.ssafy.chartflow.board.entity.ReComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReCommentRepository extends JpaRepository<ReComments, Long> {

    //FIXME: N+1 문제 발생 및 JpaRepository가 인식 못함
    //public List<ReComments> findByCommentId(long commentId);

    public ReComments findReCommentById(long reCommentId);
}
