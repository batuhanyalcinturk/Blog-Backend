package com.graysan.blog.repos;

import com.graysan.blog.entities.Like;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);
}
