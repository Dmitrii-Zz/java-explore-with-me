package ru.practicum.explore.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.comment.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByCommentIdAndUserId(long commentId, long userId);
}
