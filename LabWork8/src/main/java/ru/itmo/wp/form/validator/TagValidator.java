package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.service.PostService;

@Component
public class TagValidator implements Validator {
    private final PostService postService;

    public TagValidator(PostService postService) {
        this.postService = postService;
    }

    public boolean supports(Class<?> clazz) {
        return Post.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            Post post = (Post) target;
            String tagString = post.getTagsString();
            String[] tags = tagString.split("\\s+");
            for (String tag: tags) {
                try {
                    String[] splitTag = tag.split("[^a-zA-z]+");
                    if (splitTag.length != 1) {
                        errors.rejectValue("tagsString", "tags.are-incorrect", "Tags are incorrect!1");
                    } else {
                        for (int i = 0; i < splitTag[0].length(); ++i) {
                            if (!('a' <= splitTag[0].charAt(i) && splitTag[0].charAt(i) <= 'z' || 'A' <= splitTag[0].charAt(i) && splitTag[0].charAt(i) <= 'Z')) {
                                errors.rejectValue("tagsString", "tags.are-incorrect", "Tags are incorrect!2");
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    errors.rejectValue("tagsString", "tags.are-incorrect", "Tags are incorrect!");
                }
            }
        }
    }
}
