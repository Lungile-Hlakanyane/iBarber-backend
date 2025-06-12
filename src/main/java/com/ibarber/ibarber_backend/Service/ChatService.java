package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.ChatMessageDTO;
import java.util.List;

public interface ChatService {
    ChatMessageDTO sendMessage(ChatMessageDTO dto);
    List<ChatMessageDTO> getChatHistory(Long user1Id, Long user2Id);

}
