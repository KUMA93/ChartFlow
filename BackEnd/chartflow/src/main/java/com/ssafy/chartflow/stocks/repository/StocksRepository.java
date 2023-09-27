package com.ssafy.chartflow.stocks.repository;

import com.ssafy.chartflow.stocks.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stocks, Long> {

    Stocks findStocksById(long stocksId);

    List<Stocks> findAllByTicker(String ticker);

    @Query(value = "SELECT * FROM stocks s WHERE s.ticker = :ticker AND s.date < :date LIMIT 365", nativeQuery = true)
    List<Stocks> findAllPreviousStocks(@Param("ticker") String ticker, @Param("date") String date);
}
