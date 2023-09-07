package com.ssafy.chartflow.chatbot.controller;

import com.ssafy.chartflow.chatbot.service.ChatBotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "chatbot", description = "챗봇 API")
@RequestMapping("/chatbot")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ChatBotController {
    private final ChatBotService chatBotService;

    @PostMapping
    public ResponseEntity<?> chatBot(@RequestBody String query){
        String response = "";
        try {
            response = chatBotService.chatGPTChat(query).block();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
