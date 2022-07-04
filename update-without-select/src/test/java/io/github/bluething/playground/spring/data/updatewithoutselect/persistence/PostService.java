package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public List<Post> findAllWithNPlusOne() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<Post> posts = postEntities.stream()
                .map(postEntity ->
                    new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getPostComments().stream()
                            .map(postComment ->
                                new PostComment(postComment.getId(), postComment.getReview(), postComment.getPost().getId())).toList())
                )
                .toList();

        return posts;
    }

    @Transactional
    public List<Post> findAllWithFetch() {
        List<PostEntity> postEntities = postRepository.findAllWithFetch();
        List<Post> posts = postEntities.stream()
                .map(postEntity ->
                        new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getPostComments().stream()
                                .map(postComment ->
                                        new PostComment(postComment.getId(), postComment.getReview(), postComment.getPost().getId())).toList())
                )
                .toList();

        return posts;
    }
}
