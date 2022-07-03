package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import java.util.List;

public record Post(Integer id,
                   String title,
                   List<PostComment> comments) {
}
