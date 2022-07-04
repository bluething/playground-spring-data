package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public List<PostComment> getCommentsById(Integer id) {
        return postCommentRepository.findById(id).stream()
                .map(postComment -> new PostComment(postComment.getId(), postComment.getReview()))
                .toList();
    }
}
