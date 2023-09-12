package com.ssafy.chartflow.board.controller;

import com.ssafy.chartflow.board.dto.request.RequestModifyCommentDto;
import com.ssafy.chartflow.board.dto.request.RequestModifyReCommentDto;
import com.ssafy.chartflow.board.dto.request.RequestWriteCommentDto;
import com.ssafy.chartflow.board.dto.request.RequestWriteReCommentDto;
import com.ssafy.chartflow.board.dto.response.ResponseCommentDto;
import com.ssafy.chartflow.board.entity.Comments;
import com.ssafy.chartflow.board.service.CommentService;
import com.ssafy.chartflow.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Slf4j
public class CommentController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final JwtService jwtService;
    private final CommentService commentService;

    @Operation(summary = "댓글 목록 조회", description = "articleId에 해당하는 댓글 목록과 대댓글을 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "댓글 목록 조회 실패 - 내부 서버 오류"),
    })
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

    @Operation(summary = "댓글 작성", description = "댓글을 작성하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 작성 성공"),
            @ApiResponse(responseCode = "500", description = "댓글 작성 실패 - 내부 서버 오류"),
    })
    @PostMapping()
    public ResponseEntity<?> writeComment(@RequestHeader("Authorization") String token,
                                          @RequestBody RequestWriteCommentDto requestWriteCommentDto) {
        token = token.split(" ")[1];

        try {
            log.info("Comment Controller - 댓글 작성");
            Long userId = jwtService.extractUserId(token);
            commentService.write(requestWriteCommentDto.getArticleId(), userId, requestWriteCommentDto.getContent());
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 댓글 작성 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "대댓글 작성", description = "대댓글을 작성하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "대댓글 작성 성공"),
            @ApiResponse(responseCode = "500", description = "대댓글 작성 실패 - 내부 서버 오류"),
    })
    @PostMapping("/re")
    public ResponseEntity<?> writeReComment(@RequestHeader("Authorization") String token,
                                            @RequestBody RequestWriteReCommentDto requestWriteReCommentDto) {
        token = token.split(" ")[1];

        try {
            log.info("Comment Controller - 대댓글 작성");
            Long userId = jwtService.extractUserId(token);
            commentService.writeReComment(requestWriteReCommentDto.getCommentId(), userId, requestWriteReCommentDto.getContent());
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 대댓글 작성 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "500", description = "댓글 수정 실패 - 내부 서버 오류"),
    })
    @PatchMapping()
    public ResponseEntity<?> modifyComment(@RequestBody RequestModifyCommentDto requestModifyCommentDto) {
        try {
            log.info("Comment Controller - 댓글 수정");
            commentService.modifyComment(requestModifyCommentDto.getCommentId(), requestModifyCommentDto.getContent());
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 댓글 수정 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "대댓글 수정", description = "대댓글을 수정하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "대댓글 수정 성공"),
            @ApiResponse(responseCode = "500", description = "대댓글 수정 실패 - 내부 서버 오류"),
    })
    @PatchMapping("/re")
    public ResponseEntity<?> modifyReComment(@RequestBody RequestModifyReCommentDto requestModifyReCommentDto) {
        try {
            log.info("Comment Controller - 대댓글 수정");
            commentService.modifyReComment(requestModifyReCommentDto.getReCommentId(), requestModifyReCommentDto.getContent());
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 댓글 수정 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "댓글 삭제 실패 - 내부 서버 오류"),
    })
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long commentId) {
        try {
            log.info("Comment Controller - 댓글 삭제");
            commentService.deleteComment(commentId);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 댓글 삭제 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "대댓글 삭제", description = "대댓글을 삭제하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "대댓글 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "대댓글 삭제 실패 - 내부 서버 오류"),
    })
    @DeleteMapping("/re/{commentId}")
    public ResponseEntity<?> deleteReComment(@PathVariable long reCommentId) {
        try {
            log.info("Comment Controller - 대댓글 삭제");
            commentService.deleteReComment(reCommentId);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.info("Comment Controller - 대댓글 삭제 실패");
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
