package com.ssafy.chartflow.board.controller;

import com.ssafy.chartflow.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

}
