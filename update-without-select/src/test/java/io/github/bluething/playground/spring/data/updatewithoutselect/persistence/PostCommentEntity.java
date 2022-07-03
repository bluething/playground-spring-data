package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "post_comment")
@Getter
@Setter
public class PostCommentEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "review")
    private String review;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    public PostCommentEntity(Integer id, String review, PostEntity post) {
        this.id = id;
        this.review = review;
        this.post = post;
    }

    public PostCommentEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCommentEntity that = (PostCommentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
