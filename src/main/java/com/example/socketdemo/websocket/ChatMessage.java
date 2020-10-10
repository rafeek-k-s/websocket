package com.example.socketdemo.websocket;


public class ChatMessage {
    private MessageType type;
    private String content;
   private Long classId;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    
 public   ChatMessage(){
    	
    }
    
 public ChatMessage(String content){
    	this.content = content;
    }
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    }
