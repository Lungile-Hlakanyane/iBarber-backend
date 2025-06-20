package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
