package io.github.bluething.playground.spring.data.updatewithoutselect.persistence;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "post")
@Getter
public class PostEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostCommentEntity> postComments;

    public PostEntity(Integer id, String title, List<PostCommentEntity> postComments) {
        this.id = id;
        this.title = title;
        this.postComments = postComments;
    }

    public PostEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addComment(PostCommentEntity postComment) {
        getPostComments().add(postComment);
        postComment.setPost(this);
    }

    public void removeComment(PostCommentEntity postComment) {
        getPostComments().remove(postComment);
        postComment.setPost(null);
    }
}
