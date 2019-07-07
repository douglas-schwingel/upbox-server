package br.com.upboxserver.service;

import br.com.upboxserver.models.User;
import br.com.upboxserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

//    public User find(User user) {
//        return repository.findById(user.getUsername());
//    }

}
