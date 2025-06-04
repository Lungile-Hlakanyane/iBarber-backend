package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.PostDTO;
import com.ibarber.ibarber_backend.entity.Post;
import com.ibarber.ibarber_backend.entity.User;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class PostMapper {
    public PostDTO toDto(Post post) {
        if (post == null) return null;
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setAudience(post.getAudience());
        dto.setImageUrl(post.getImageUrl());
        dto.setUserId(post.getUserId());
        dto.setCreatedAt(post.getCreatedAt());
        return dto;
    }
    public Post toEntity(PostDTO dto, User user) {
        if (dto == null) return null;
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setAudience(dto.getAudience());
        post.setImageUrl(dto.getImageUrl());
        post.setId(dto.getId());
        post.setUserId(dto.getUserId());
        post.setCreatedAt(LocalDateTime.now());
        return post;
    }
}
