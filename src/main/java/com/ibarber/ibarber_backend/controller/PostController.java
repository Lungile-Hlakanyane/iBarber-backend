package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.ImageStorageService;
import com.ibarber.ibarber_backend.Service.PostService;
import com.ibarber.ibarber_backend.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public PostController(PostService postService, ImageStorageService imageStorageService) {
        this.postService = postService;
        this.imageStorageService = imageStorageService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<PostDTO> createPost(
            @RequestPart("post") PostDTO postDTO,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = imageStorageService.storeImage(imageFile);
            postDTO.setImageUrl(imageUrl);
        }

        PostDTO createdPost = postService.createPost(postDTO);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        PostDTO post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
