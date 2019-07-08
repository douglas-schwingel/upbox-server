package br.com.upboxserver.user.controller;

import br.com.upboxserver.user.model.User;
import br.com.upboxserver.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserControllerFacade {

    private UserService service;

    public UserControllerFacade(UserService service) {
        this.service = service;
    }

    User saveUser(User user) {
        return service.saveUser(user);
    }

    User find(String username) {
        return service.find(username);
    }

    User delete(User user) {
        return service.delete(user);
    }

    User update(String userToBeUpdated, User user) {
        return service.update(userToBeUpdated, user);
    }
}
