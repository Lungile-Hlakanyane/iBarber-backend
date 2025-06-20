package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.ChatService;
import com.ibarber.ibarber_backend.dto.ChatMessageDTO;
import com.ibarber.ibarber_backend.entity.ChatMessage;
import com.ibarber.ibarber_backend.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        dto.setId(saved.getId());
        return dto;
    }
    @Override
    public List<ChatMessageDTO> getChatHistory(Long user1Id, Long user2Id) {
        List<ChatMessage> messages = chatMessageRepository
                .findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(user1Id, user2Id, user1Id, user2Id);
        return messages.stream().map(msg -> {
            ChatMessageDTO dto = new ChatMessageDTO();
            dto.setId(msg.getId());
            dto.setSenderId(msg.getSenderId());
            dto.setReceiverId(msg.getReceiverId());
            dto.setContent(msg.getContent());
            dto.setTimestamp(msg.getTimestamp());
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public boolean deleteMessage(Long id) {
        Optional<ChatMessage> message = chatMessageRepository.findById(id);
        if (message.isPresent()) {
            chatMessageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
