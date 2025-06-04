package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.ChatService;
import com.ibarber.ibarber_backend.dto.ChatMessageDTO;
import com.ibarber.ibarber_backend.entity.ChatMessage;
import com.ibarber.ibarber_backend.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImp implements ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Override
    public ChatMessageDTO sendMessage(ChatMessageDTO dto) {
        ChatMessage message = new ChatMessage();
        message.setSenderId(dto.getSenderId());
        message.setReceiverId(dto.getReceiverId());
        message.setContent(dto.getContent());
        message.setTimestamp(LocalDateTime.now());
        ChatMessage saved = chatMessageRepository.save(message);
        dto.setTimestamp(saved.getTimestamp());
        return dto;
    }
    @Override
    public List<ChatMessageDTO> getChatHistory(Long user1Id, Long user2Id) {
        List<ChatMessage> messages = chatMessageRepository
                .findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(user1Id, user2Id, user1Id, user2Id);
        return messages.stream().map(msg -> new ChatMessageDTO(
                msg.getSenderId(), msg.getReceiverId(), msg.getContent(), msg.getTimestamp()
        )).collect(Collectors.toList());
    }
}
