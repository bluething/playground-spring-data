package io.github.bluething.playground.spring.data.updatewithoutselect;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import io.github.bluething.playground.spring.data.updatewithoutselect.persistence.PostCommentService;
import io.github.bluething.playground.spring.data.updatewithoutselect.persistence.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NPlusOneSelectTes {
    @Autowired
    private PostService postService;

    @Autowired
    private PostCommentService postCommentService;

    @BeforeEach
    public void beforeEach() {
        SQLStatementCountValidator.reset();
    }

    @Test
    public void findAllWithNPlusOneWillHaveThreeSelectQueries() {
        postService.findAllWithNPlusOne();
        SQLStatementCountValidator.assertSelectCount(2);
    }

    @Test
    public void findAllWithFetchWillHaveOneSelectQuery() {
        postService.findAllWithFetch();
        SQLStatementCountValidator.assertSelectCount(1);
    }

    @Test
    public void getCommentsByIdWillHaveOneSelectQuery() {
        postCommentService.getCommentsById(1);
        SQLStatementCountValidator.assertSelectCount(1);
    }
}
