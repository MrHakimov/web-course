package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.StatusCredentials;
import ru.itmo.wp.service.UserService;

@Component
public class StatusCredentialsChangeValidator implements Validator {
    private final UserService userService;

    public StatusCredentialsChangeValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return StatusCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            StatusCredentials changeStatusForm = (StatusCredentials) target;
        }
    }
}
