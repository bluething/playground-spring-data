package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    @Query("SELECT p FROM PostEntity p JOIN FETCH p.postComments")
    List<PostEntity> findAllWithFetch();
}
