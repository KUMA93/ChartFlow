package com.ssafy.chartflow.board.controller;

import com.ssafy.chartflow.board.dto.request.RequestWriteCommentDto;
import com.ssafy.chartflow.board.dto.request.RequestWriteReCommentDto;
import com.ssafy.chartflow.board.dto.response.ResponseCommentDto;
import com.ssafy.chartflow.board.entity.Comments;
import com.ssafy.chartflow.board.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "comment", description = "댓글 API")
@RequestMapping("/comment")
@CrossOrigin("*")
@Slf4j
public class CommentController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    CommentService commentService;

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getCommentList(@PathVariable long articleId){
        try {
            log.info("Comment Controller - 댓글 목록 조회");
            List<ResponseCommentDto> comments = commentService.searchAll(articleId);
            return new ResponseEntity<List<ResponseCommentDto>>(comments, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 댓글 목록 조회 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> writeComment(@RequestBody RequestWriteCommentDto requestWriteCommentDto) {
        try {
            log.info("Comment Controller - 댓글 작성");
            commentService.write(requestWriteCommentDto.getArticleId(), requestWriteCommentDto.getUserId(), requestWriteCommentDto.getContent());
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 댓글 작성 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recomment")
    public ResponseEntity<?> writeReComment(@RequestBody RequestWriteReCommentDto requestWriteReCommentDto) {
        try {
            log.info("Comment Controller - 대댓글 작성");
            commentService.writeReComment(requestWriteReCommentDto.getCommentId(), requestWriteReCommentDto.getUserId(), requestWriteReCommentDto.getContent());
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 대댓글 작성 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
