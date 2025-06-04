package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    CommentDTO addComment(CommentDTO commentDTO);
    List<CommentDTO> getCommentsByPostId(Long postId);
}
