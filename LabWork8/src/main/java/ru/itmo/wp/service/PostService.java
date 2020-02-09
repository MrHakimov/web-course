package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post findById(long id) {
        Post post = null;
        try {
            post = postRepository.findById(id);
        } catch (Exception e) {
            // No operations
        }
        return post;
    }

    public void writeComment(Post post, User user, Comment comment) {
        post.addComment(user, comment);
        postRepository.save(post);
    }

}
