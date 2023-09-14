package com.ssafy.chartflow.board.controller;

import com.ssafy.chartflow.board.dto.request.RequestLikeDto;
import com.ssafy.chartflow.board.dto.request.RequestModifyArticleDto;
import com.ssafy.chartflow.board.dto.request.RequestWriteArticleDto;
import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;
import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.service.ArticleService;
import com.ssafy.chartflow.exception.LikeDuplicateException;
import com.ssafy.chartflow.exception.NoSuchLikeException;
import com.ssafy.chartflow.security.service.JwtService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    private final ArticleService articleService;
    private final JwtService jwtService;
    //전체 글 조회
    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> listArticle(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Map<String,Object> response = new HashMap<>();

        try{
            Pageable pageable = PageRequest.of(page,size);
            response.put("articles",pageable);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //글 작성
    @PostMapping
    public ResponseEntity<Map<String,Object>> writeArticle(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String token,
            @RequestBody RequestWriteArticleDto requestWriteArticleDto
    ){
        Map<String,Object> response = new HashMap<>();
        token = token.split(" ")[1];

        try {
            Long userId = jwtService.extractUserId(token);
            String title = requestWriteArticleDto.getTitle();
            String content = requestWriteArticleDto.getContent();
            articleService.writeArticle(userId, title, content);

            response.put("status", "success");
            response.put("message", "Article successfully created.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //글 수정
    @PatchMapping
    public ResponseEntity<Map<String,Object>> modifyArticle(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody RequestModifyArticleDto requestModifyArticleDto
    ){
        Map<String,Object> response = new HashMap<>();

        try {
            long articleId = requestModifyArticleDto.getArticleId();
            String content = requestModifyArticleDto.getContent();
            String title = requestModifyArticleDto.getTitle();
            articleService.modifyArticle(articleId, title, content);

            response.put("status", "success");
            response.put("message", "Article successfully modified.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //특정 글 조회
    @GetMapping("/{articleId}")
    public ResponseEntity<Map<String,Object>> readArticleByArticleId(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String token,
            @PathVariable long articleId
    ){
        Map<String,Object> response = new HashMap<>();

        try {
            Article article = articleService.findArticleByArticleId(articleId);
            ArticleResponseDto articleResponseDto = new ArticleResponseDto();

            String title = article.getTitle();
            String nickname = article.getUser().getNickname();
            LocalDateTime registerTime = article.getRegisterTime();
            int views = article.getViews();
            long id = article.getId();
            String content = article.getContent();

            articleResponseDto.setTitle(title);
            articleResponseDto.setNickName(nickname);
            articleResponseDto.setRegisterTime(registerTime);
            article.setViews(views);
            article.setId(id);
            article.setContent(content);

            response.put("article", articleResponseDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //글 삭제
    @DeleteMapping
    public ResponseEntity<Map<String,Object>> deleteArticle(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody RequestModifyArticleDto requestModifyArticleDto
    ){
        Map<String,Object> response = new HashMap<>();
        try {
            long userId = jwtService.extractUserId(jwtToken);
            List<Article> userArticles = articleService.findAllArticleByUserId(userId);

            for(Article userArticle : userArticles){
                if(userArticle.getUser().getId() == userId){
                    articleService.deleteArticle(requestModifyArticleDto.getArticleId());
                    response.put("status", "success");
                    response.put("message", "Article successfully deleted.");
                    break;
                }
            }

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/like")
    public ResponseEntity<Map<String,Object>> likeArticle(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String token,
            @RequestBody Long articleId
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

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/like")
    public ResponseEntity<Map<String,Object>> withdrawLike(
            @RequestBody long likeId
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
