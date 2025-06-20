package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.ChatService;
import com.ibarber.ibarber_backend.dto.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        boolean deleted = chatService.deleteMessage(id);
        return deleted ?
                ResponseEntity.ok("Message deleted successfully") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
    }

}
