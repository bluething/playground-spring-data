package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Integer> {
}
