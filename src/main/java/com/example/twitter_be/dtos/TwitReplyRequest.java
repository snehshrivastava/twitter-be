package com.example.twitter_be.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TwitReplyRequest {
    private String content;
    private long replyForId;
    private long user_id;
    private LocalDateTime createdAt;

}
