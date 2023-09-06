package com.ssafy.chartflow.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWriteReCommentDto {
    private int userId;
    private int commentId;
    private String content;
}
