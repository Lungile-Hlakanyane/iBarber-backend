package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.CommentService;
import com.ibarber.ibarber_backend.dto.CommentDTO;
import com.ibarber.ibarber_backend.entity.Comment;
import com.ibarber.ibarber_backend.entity.Post;
import com.ibarber.ibarber_backend.repository.CommentRepository;
import com.ibarber.ibarber_backend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setUserId(commentDTO.getUserId());
        comment.setPost(post);
        Comment saved = commentRepository.save(comment);
        commentDTO.setId(saved.getId());
        commentDTO.setCreatedAt(saved.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
        return commentDTO;
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return commentRepository.findByPost(post)
                .stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    dto.setId(comment.getId());
                    dto.setContent(comment.getContent());
                    dto.setUserId(comment.getUserId());
                    dto.setPostId(postId);
                    dto.setCreatedAt(comment.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
                    return dto;
                }).collect(Collectors.toList());
    }
}
