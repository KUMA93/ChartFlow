package com.ssafy.chartflow.user.repository;

public interface CustomUserRepository {

    public void decreaseCoinByUserId(long userId);
    public void increaseCoinByUserId(long userId);

    public void increaseAll();
}
