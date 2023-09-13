package com.ssafy.chartflow.emblem.repository;

import com.ssafy.chartflow.emblem.entity.Emblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmblemRepository extends JpaRepository<Emblem, Long> {

    Emblem findByName(String title);

}
