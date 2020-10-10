package com.example.socketdemo.websocket;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface ChatService {
	public void studentJoin(ChatRequest requestDTO ,SimpMessageHeaderAccessor headerAccessor) ;
}
