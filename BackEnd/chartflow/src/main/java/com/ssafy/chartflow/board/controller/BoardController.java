package com.ssafy.chartflow.board.controller;

import com.ssafy.chartflow.board.dto.request.RequestDeleteArticleDto;
import com.ssafy.chartflow.board.dto.request.RequestLikeDto;
import com.ssafy.chartflow.board.dto.request.RequestModifyArticleDto;
import com.ssafy.chartflow.board.dto.request.RequestWriteArticleDto;
import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;
import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.entity.ArticleTag;
import com.ssafy.chartflow.board.service.ArticleService;
import com.ssafy.chartflow.exception.LikeDuplicateException;
import com.ssafy.chartflow.exception.NoSuchLikeException;
import com.ssafy.chartflow.security.service.JwtService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    //키워드 검색 목록 조회
    @GetMapping("/list/{keyword}")
    public ResponseEntity<Map<String,Object>> getAllKeywordArticles(Pageable pageable,@PathVariable("keyword") String keyword){
        Map<String,Object> response = new HashMap<>();

        try{
            Page<Article> allArticles = articleService.getAllKeywordArticles(keyword,pageable);
            List<ArticleResponseDto> responseArticles = new ArrayList<>();

            for(Article article : allArticles){
                ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                        .id(article.getId())
                        .nickName(article.getUser().getNickname())
                        .views(article.getViews())
                        .likes(article.getLikes().size())
                        .content(article.getContent())
                        .title(article.getTitle())
                        .registerTime(article.getRegisterTime())
                        .tag(article.getTag())
                        .isDeleted(article.isDeleted())
                        .build();
                responseArticles.add(articleResponseDto);
            }
            response.put("articles",responseArticles);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> getAllArticles(Pageable pageable){
        Map<String,Object> response = new HashMap<>();

        try{
            Page<Article> allArticles = articleService.getAllArticles(pageable);
            List<ArticleResponseDto> responseArticles = new ArrayList<>();

            for(Article article : allArticles){
                ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                        .id(article.getId())
                        .nickName(article.getUser().getNickname())
                        .views(article.getViews())
                        .likes(article.getLikes().size())
                        .content(article.getContent())
                        .title(article.getTitle())
                        .registerTime(article.getRegisterTime())
                        .tag(article.getTag())
                        .isDeleted(article.isDeleted())
                        .build();
                responseArticles.add(articleResponseDto);
            }
            response.put("articles",responseArticles);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
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
            ArticleTag tag = requestWriteArticleDto.getTag();
            String title = requestWriteArticleDto.getTitle();
            String content = requestWriteArticleDto.getContent();
            articleService.writeArticle(userId, tag, title, content);

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
            long userId = jwtService.extractUserId(jwtToken);
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
            ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                    .id(article.getId())
                    .nickName(article.getUser().getNickname())
                    .views(article.getViews())
                    .likes(article.getLikes().size())
                    .content(article.getContent())
                    .title(article.getTitle())
                    .registerTime(article.getRegisterTime())
                    .tag(article.getTag())
                    .build();




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
            @RequestBody RequestDeleteArticleDto requestDeleteArticleDto
    ){
        Map<String,Object> response = new HashMap<>();
        try {
            long userId = jwtService.extractUserId(jwtToken);
            Article article = articleService.findArticleByArticleId(requestDeleteArticleDto.getArticleId());

            if(article.getUser().getId() == userId){
                //todo
                articleService.deleteArticle(article.getId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                response.put("message","삭제 권한이 없습니다.");
                return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
            }
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
            @RequestBody RequestLikeDto requestLikeDto
    ){
        Map<String,Object> response = new HashMap<>();
        token = token.split(" ")[1];
        try {
            Long userId = jwtService.extractUserId(token);
            articleService.likeArticle(userId, requestLikeDto.getArticleId());
            response.put("message", "success");
        } catch (LikeDuplicateException e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/like")
    public ResponseEntity<Map<String,Object>> withdrawLike(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String token,
            @RequestBody RequestLikeDto requestLikeDto
    ){
        Map<String,Object> response = new HashMap<>();
        token = token.split(" ")[1];
        try {
            Long userId = jwtService.extractUserId(token);
            articleService.withdrawLike(userId,requestLikeDto.getArticleId());

            response.put("message", "success");
        } catch (Exception e) {
            response.put("message", "좋아요 취소 오류");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
