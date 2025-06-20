package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.ChatService;
import com.ibarber.ibarber_backend.dto.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessageDTO chatMessage) {
        ChatMessageDTO savedMessage = chatService.sendMessage(chatMessage);
        messagingTemplate.convertAndSend("/topic/messages/" + chatMessage.getReceiverId(), chatMessage);
    }


}
