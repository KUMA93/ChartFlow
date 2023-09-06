package com.ssafy.chartflow.board.service;

import com.ssafy.chartflow.board.dto.response.ArticleResponseDto;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImp implements BoardService{
    @Override
    public ArticleResponseDto getArticle(String email, String content) {
        return null;
    }

    @Override
    public ArticleResponseDto modifyArticle(long articleId, String content) {
        return null;
    }

    @Override
    public void deleteArticle(long articleId) {

    }


}
