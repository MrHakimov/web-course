package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.itmo.wp.repository.TagRepository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/** @noinspection unused*/
@Entity
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    private String text;

    @CreationTimestamp
    private Date creationTime;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @OrderBy("name")
    private Set<Tag> tags;

    private String tagsString = "";

    public String getTagsString() {
        return tagsString;
    }

    public void setTagsString(String tagsString) {
        this.tagsString = tagsString;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    private Set<Tag> parseTags(String tagsString) {
        String[] myTagsString = tagsString.split("\\s+");
        Set<String> myTagsSet = new TreeSet<>(Arrays.asList(myTagsString));
        Set<Tag> myTags = new HashSet<>();
        for (String tag : myTagsSet) {
            myTags.add(new Tag(tag));
        }

        return myTags;
    }

    public Set<Tag> getTags() {
        if (tags == null || tags.isEmpty() && !tagsString.isEmpty()) {
            return parseTags(tagsString);
        } else {
            return tags;
        }
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        getTags().add(tag);
    }

    public List<Comment> getComments() {
        Collections.reverse(comments);
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(User user, Comment comment) {
        comment.setPost(this);
        comment.setUser(user);
        getComments().add(comment);
    }
}
