package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @PostMapping("/{postId}/{userId}")
    public void likePost(@PathVariable Long postId, @PathVariable Long userId) {
        likeService.likePost(postId, userId);
    }
    @DeleteMapping("/{postId}/{userId}")
    public void unlikePost(@PathVariable Long postId, @PathVariable Long userId) {
        likeService.unlikePost(postId, userId);
    }
    @GetMapping("/{postId}/{userId}")
    public boolean isLiked(@PathVariable Long postId, @PathVariable Long userId) {
        return likeService.isPostLikedByUser(postId, userId);
    }
    @GetMapping("/count/{postId}")
    public long getLikesCount(@PathVariable Long postId) {
        return likeService.countLikes(postId);
    }
}
