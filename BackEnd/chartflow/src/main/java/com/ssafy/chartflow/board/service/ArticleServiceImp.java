package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;
import com.ssafy.chartflow.board.entity.Article;
import com.ssafy.chartflow.board.entity.ArticleTag;
import com.ssafy.chartflow.board.entity.Likes;
import com.ssafy.chartflow.board.repository.ArticleRepository;
import com.ssafy.chartflow.board.repository.CustomLikeRepository;
import com.ssafy.chartflow.board.repository.LikeRepository;
import com.ssafy.chartflow.exception.LikeDuplicateException;
import com.ssafy.chartflow.exception.NoSuchLikeException;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor

public class ArticleServiceImp implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;


    @Override
    public Page<Article> getAllKeywordArticles(String keyword, Pageable pageable) {
        return articleRepository.findAllByTitleLike("%" + keyword +"%",pageable);
    }

    @Override
    public Page<Article> getAllArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public void writeArticle(long userId, ArticleTag tag, String title, String content) {
        User user = userRepository.findUserById(userId);
        Article article = new Article();
        article.setUser(user);
        article.setRegisterTime(LocalDateTime.now());
        article.setTitle(title);
        article.setContent(content);
        article.setTag(tag);

        userRepository.save(user);
        articleRepository.save(article);
    }

    @Override
    public void modifyArticle(long articleId,String title,String content) {

        Article article = articleRepository.findArticleById(articleId);
        article.setContent(content);
        article.setTitle(title);
        articleRepository.save(article);

    }

    @Override
    public void deleteArticle(long articleId) {
        Article article = articleRepository.findArticleById(articleId);
        article.setDeleted(true);
        articleRepository.save(article);
    }

    @Override
    public List<Article> findAllArticleByUserId(long userId) {
        return articleRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void likeArticle(long userId, long articleId){

        if (likeRepository.findLikesByUserIdAndArticleId(userId,articleId) != null){
            throw new LikeDuplicateException();
        }

        User user = userRepository.findUserById(userId);
        Article article = articleRepository.findArticleById(articleId);

        Likes likes = new Likes();
        likes.setArticle(article);
        likes.setUser(user);
        likeRepository.save(likes);
    }

    @Override
    public void withdrawLike(long userId,long articleId) {
        likeRepository.deleteByUserIdAndArticleId(userId,articleId);
    }

    @Override
    public Article findArticleByArticleId(long articleId) {
        return articleRepository.findArticleById(articleId);
    }

}
