package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.PostDTO;
import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long id);
    void deletePost(Long id);
}
