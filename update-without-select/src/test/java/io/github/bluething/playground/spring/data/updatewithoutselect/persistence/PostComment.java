package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

public record PostComment(Integer id,
                          String review,
                          Integer postId) {
}
