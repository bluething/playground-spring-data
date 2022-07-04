package io.github.bluething.playground.spring.data.updatewithoutselect;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import io.github.bluething.playground.spring.data.updatewithoutselect.persistence.PostComment;
import io.github.bluething.playground.spring.data.updatewithoutselect.persistence.PostCommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NPlusOneUpdateTes {
    @Autowired
    private PostCommentService postCommentService;

    @BeforeEach
    public void beforeEach() {
        SQLStatementCountValidator.reset();
    }

    @Test
    public void updatePostCommentWitNPlusOneWillHaveOneQuerySelectAndOneQueryUpdate() {
        PostComment newPostComment = new PostComment(1, "It's bad", 1);
        postCommentService.updatePostCommentWitNPlusOne(newPostComment);
        SQLStatementCountValidator.assertSelectCount(1);
        SQLStatementCountValidator.assertUpdateCount(1);
    }

    @Test
    public void updatePostCommentWitNPlusOneWillHaveOneQueryUpdate() {
        PostComment newPostComment = new PostComment(1, "It's bad", 1);
        postCommentService.updatePostCommentWithoutNPlusOne(newPostComment);
        SQLStatementCountValidator.assertSelectCount(0);
        SQLStatementCountValidator.assertUpdateCount(1);
    }
}
