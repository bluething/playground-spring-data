package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository, PostRepository postRepository) {
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
    }

    public List<PostComment> getCommentsById(Integer id) {
        return postCommentRepository.findById(id).stream()
                .map(postComment -> new PostComment(postComment.getId(), postComment.getReview(), postComment.getPost().getId()))
                .toList();
    }

    @Transactional
    public void updatePostCommentWitNPlusOne(PostComment postComment) {
        PostCommentEntity postCommentEntity = postCommentRepository.findById(postComment.id()).get();
        postCommentEntity.setReview(postComment.review());
        postCommentEntity.setPost(postRepository.findById(postComment.postId()).get());
        postCommentRepository.save(postCommentEntity);
    }

    @Transactional
    public void updatePostCommentWithoutNPlusOne(PostComment postComment) {
       postCommentRepository.updateReview(postComment.id(), postComment.review());
    }
}
