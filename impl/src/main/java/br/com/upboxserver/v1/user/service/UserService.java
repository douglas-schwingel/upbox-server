package br.com.upboxserver.v1.user.service;

import br.com.upboxserver.v1.user.exception.InvalidUserException;
import br.com.upboxserver.v1.user.exception.UserNotFoundException;
import br.com.upboxserver.v1.user.model.User;
import br.com.upboxserver.v1.user.repository.UserRepository;
import br.com.upboxserver.v1.user.util.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        if (UserUtils.isValid(user)) {
            return repository.save(user);
        }
        throw new InvalidUserException("Invalid user. Please, check the information and try again");
    }

    public User find(String username) {
        User user = repository.findByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("No user with such username. Please, verify the information and try again.");
        }
        return user;
    }

    public Long delete(String username) {
        find(username);
        return repository.deleteByUsername(username);
    }

    public User update(User user) {
        User returned = repository.findByUsername(user.getUsername());
        if(returned != null) {
            return repository.save(user);
        }
        throw new UserNotFoundException("No user with such username. Please, verify the information and try again");
    }
}
