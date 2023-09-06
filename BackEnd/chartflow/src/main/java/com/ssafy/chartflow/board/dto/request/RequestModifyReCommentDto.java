package com.ssafy.chartflow.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestModifyReCommentDto {
    private long reCommentId;
    private String content;
}
