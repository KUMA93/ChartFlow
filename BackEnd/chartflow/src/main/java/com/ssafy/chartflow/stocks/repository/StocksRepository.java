package com.ssafy.chartflow.stocks.repository;

import com.ssafy.chartflow.stocks.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stocks, Long> {

    Stocks findStocksById(long stocksId);

    List<Stocks> findAllByTicker(String ticker);
}
