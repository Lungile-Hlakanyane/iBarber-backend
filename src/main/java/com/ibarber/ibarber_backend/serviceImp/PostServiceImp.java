package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.PostService;
import com.ibarber.ibarber_backend.dto.PostDTO;
import com.ibarber.ibarber_backend.entity.Post;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.mapper.PostMapper;
import com.ibarber.ibarber_backend.repository.PostRepository;
import com.ibarber.ibarber_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    @Autowired
    public PostServiceImp(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }
    @Override
    public PostDTO createPost(PostDTO postDTO) {
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postMapper.toEntity(postDTO, user);
        return postMapper.toDto(postRepository.save(post));
    }
    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public PostDTO getPostById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
