package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @noinspection UnstableApiUsage
 */
public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private static final String PASSWORD_SALT = "177d4b5f2e4f4edafa7404533973c04c513ac619";

    public long findCount() {
        return userRepository.findAll().size();
    }

    public void validateRegistration(User user, String password, String passwordConfirmation,
                                     String email) throws ValidationException {
        int sobakas = 0;
        for (int i = 0; i < email.length(); ++i) {
            if (email.charAt(i) == '@') {
                ++sobakas;
            }
        }

        if (sobakas != 1) {
            throw new ValidationException("E-mail should contain exactly 1 \'@\' symbol!");
        }

        if (userRepository.findByLogin(email) != null) {
            throw new ValidationException("E-mail is already in use");
        }

        if (!password.equals(passwordConfirmation)) {
            throw new ValidationException("Passwords are not matching");
        }
        if (Strings.isNullOrEmpty(user.getLogin())) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8 letters");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (Strings.isNullOrEmpty(password)) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Login can't be shorter than 4 characters");
        }
        if (password.length() > 12) {
            throw new ValidationException("Login can't be longer than 12 characters");
        }
    }

    public void register(User user, String password) throws ValidationException {
        userRepository.save(user, getPasswordSha(password));
    }

    private String getPasswordSha(String password) throws ValidationException {
        if (password.isEmpty()) {
            throw new ValidationException("Invalid login or password");
        }
        return Hashing.sha256().hashBytes((PASSWORD_SALT + password).getBytes(StandardCharsets.UTF_8)).toString();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User validateAndFindByLoginAndPassword(String login, String password) throws ValidationException {
        try {
            User user = userRepository.findByLoginAndPasswordSha(login, getPasswordSha(password));
            if (user == null) {
                throw new ValidationException("Invalid login or password");
            }
            return user;
        } catch (RepositoryException e) {
            throw new ValidationException("Invalid login or password");
        }
    }
}
