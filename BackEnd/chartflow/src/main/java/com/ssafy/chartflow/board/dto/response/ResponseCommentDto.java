package com.ssafy.chartflow.board.dto.response;

import com.ssafy.chartflow.board.entity.ReComments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentDto {
    private long commentId;
    private long userId;
    private String nickname;
    private String content;
    private LocalDateTime registerTime;
    private int cancel;
    private int modify;
    private List<ResponseReCommentDto> reComments;
}
