package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.LikeService;
import com.ibarber.ibarber_backend.entity.Like;
import com.ibarber.ibarber_backend.entity.Post;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.repository.LikeRepository;
import com.ibarber.ibarber_backend.repository.PostRepository;
import com.ibarber.ibarber_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!likeRepository.existsByPostAndUser(post, user)) {
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(like);
        }
    }

    @Override
    public void unlikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        likeRepository.findByPostAndUser(post, user)
                .ifPresent(likeRepository::delete);
    }

    @Override
    public boolean isPostLikedByUser(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return likeRepository.existsByPostAndUser(post, user);
    }
    @Override
    public long countLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return likeRepository.countByPost(post);
    }
}
