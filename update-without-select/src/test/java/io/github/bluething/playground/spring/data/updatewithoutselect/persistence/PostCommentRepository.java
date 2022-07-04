package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Integer> {
    @Modifying
    @Query("update PostCommentEntity set review = ?2 where id = ?1")
    void updateReview(Integer id, String review);
}
