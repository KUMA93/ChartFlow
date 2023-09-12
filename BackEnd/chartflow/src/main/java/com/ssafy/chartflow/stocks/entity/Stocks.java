package com.ssafy.chartflow.stocks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "stocks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stocks_id")
    private long id;

    private String name;

    private LocalDate date;

    private String ticker;

    @Column(name = "open_price")
    private int openPrice;

    @Column(name = "highest_price")
    private int highestPrice;

    @Column(name = "lowest_price")
    private int lowestPrice;

    @Column(name = "closing_price")
    private int closingPrice;

    private int volumes;

    private float rate;

}
