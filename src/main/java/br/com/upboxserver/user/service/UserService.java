package br.com.upboxserver.user.service;

import br.com.upboxserver.user.exception.InvalidUserException;
import br.com.upboxserver.user.exception.UserNotFoundException;
import br.com.upboxserver.user.model.User;
import br.com.upboxserver.user.repository.UserRepository;
import br.com.upboxserver.user.util.UserUtils;
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

    public User delete(User user) {
        if (UserUtils.isValid(user)){
            return repository.deleteByUsername(user.getUsername());
        }
        throw new InvalidUserException("Invalid user. Please, check the information and try again");
    }

    public User update(String userToBeUpdated, User user) {
        return null;
    }
}
