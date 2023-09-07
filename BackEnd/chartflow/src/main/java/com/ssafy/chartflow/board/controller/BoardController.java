package com.ssafy.chartflow.board.controller;

import com.ssafy.chartflow.board.dto.request.RequestLikeDto;
import com.ssafy.chartflow.board.dto.request.RequestWriteArticleDto;
import com.ssafy.chartflow.board.service.ArticleService;
import com.ssafy.chartflow.exception.LikeDuplicateException;
import com.ssafy.chartflow.exception.NoSuchLikeException;
import com.ssafy.chartflow.security.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    private final ArticleService articleService;
    private final JwtService jwtService;
    //글 작성
    @PostMapping
    public ResponseEntity<Map<String,Object>> writeArticle(
            @RequestBody RequestWriteArticleDto requestWriteArticleDto
            ){
        Map<String,Object> response = new HashMap<>();

        try {
            String email = requestWriteArticleDto.getEmail();
            String title = requestWriteArticleDto.getTitle();
            String content = requestWriteArticleDto.getContent();

            articleService.writeArticle(email, title, content);

            response.put("status", "success");
            response.put("message", "Article successfully created.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            // 로깅은 좀 더 상세하게 하시면 좋습니다.
            // logger.error("Error occurred while creating article", e);

            response.put("status", "error");
            response.put("message", e.getMessage()); // 실제 프로덕션에서는 이렇게 바로 예외 메시지를 반환하지 마세요.
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> likeArticle(
            @RequestHeader("Authorization") String token,
            Long articleId
    ){
        Map<String,Object> response = new HashMap<>();
        token = token.split(" ")[1];
        try {
            Long userId = jwtService.extractUserId(token);
            articleService.likeArticle(userId, articleId);
            response.put("message", "success");
        } catch (LikeDuplicateException e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public ResponseEntity<Map<String,Object>> withdrawLike(
            long likeId
    ){
        Map<String,Object> response = new HashMap<>();
        try {
            articleService.withdrawLike(likeId);
            response.put("message", "success");
        } catch (Exception e) {
            response.put("message", "좋아요 취소 오류");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
