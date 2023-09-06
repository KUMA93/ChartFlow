package com.ssafy.chartflow.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWriteCommentDto {
    private long userId;
    private long articleId;
    private String content;
}
