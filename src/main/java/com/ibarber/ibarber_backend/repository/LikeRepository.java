package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.Like;
import com.ibarber.ibarber_backend.entity.Post;
import com.ibarber.ibarber_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface LikeRepository extends JpaRepository<Like, Long> {
    long countByPost(Post post);
    boolean existsByPostAndUser(Post post, User user);
    Optional<Like> findByPostAndUser(Post post, User user);
}
