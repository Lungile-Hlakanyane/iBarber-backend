package com.ibarber.ibarber_backend.Service;

public interface LikeService {
    void likePost(Long postId, Long userId);
    void unlikePost(Long postId, Long userId);
    boolean isPostLikedByUser(Long postId, Long userId);
    long countLikes(Long postId);
}
