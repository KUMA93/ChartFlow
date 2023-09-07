package com.ssafy.chartflow.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReCommentDto {
    private long reCommentId;
    private long commentId;
    private long userId;
    private String content;
    private LocalDateTime registerTime;
    private int cancel;
    private int modify;
}
