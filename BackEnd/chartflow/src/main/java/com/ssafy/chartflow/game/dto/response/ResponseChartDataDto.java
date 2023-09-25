package com.ssafy.chartflow.game.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChartDataDto {

    private long id;
    private String name;
    private String ticker;
    private String date;
    private int openPrice;
    private int closingPrice;
    private int highestPrice;
    private int lowestPrice;
    private int volumes;
    private double rate;

}
