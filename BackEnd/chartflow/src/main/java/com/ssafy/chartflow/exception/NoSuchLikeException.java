package com.ssafy.chartflow.exception;

public class NoSuchLikeException extends RuntimeException {
    public NoSuchLikeException(){
        super("좋아요 내역이 없음 - 삭제오류");
    }
}

