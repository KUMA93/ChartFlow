package com.ssafy.chartflow.emblem.repository;

import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;

public interface CustomUserEmblemRepository {
    public void equipEmblem(UserEmblem emblem);
    public void unequipEmblem(UserEmblem emblem);

}
