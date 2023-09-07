package com.ssafy.chartflow.exception;

public class LikeDuplicateException extends RuntimeException {
    public LikeDuplicateException(){
        super("이미 좋아요를 눌렀습니다.");
    }
}
