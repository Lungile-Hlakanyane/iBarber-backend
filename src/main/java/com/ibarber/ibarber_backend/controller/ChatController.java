package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.ChatService;
import com.ibarber.ibarber_backend.dto.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/chats")
@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @PostMapping("/send")
    public ChatMessageDTO sendMessage(@RequestBody ChatMessageDTO dto) {
        return chatService.sendMessage(dto);
    }
    @GetMapping("/messages")
    public List<ChatMessageDTO> getChatMessages(
            @RequestParam Long senderId,
            @RequestParam Long receiverId
    ) {
        return chatService.getChatHistory(senderId, receiverId);
    }


}
