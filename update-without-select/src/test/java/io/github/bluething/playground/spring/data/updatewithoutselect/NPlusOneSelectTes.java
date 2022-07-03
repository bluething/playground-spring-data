package io.github.bluething.playground.spring.data.updatewithoutselect;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import io.github.bluething.playground.spring.data.updatewithoutselect.persistence.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class NPlusOneSelectTes {
    @Autowired
    private PostService postService;

    @BeforeEach
    public void beforeEach() {
        SQLStatementCountValidator.reset();
    }

    @Sql(
            scripts = "/init.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
            scripts = "/cleanup.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    @Test
    public void findAllWithNPlusOneWillHaveThreeSelectQueries() {
        postService.findAllWithNPlusOne();
        SQLStatementCountValidator.assertSelectCount(3);
    }
}
