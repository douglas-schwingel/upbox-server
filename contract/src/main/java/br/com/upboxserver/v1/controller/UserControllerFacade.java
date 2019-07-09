package br.com.upboxserver.v1.controller;

import br.com.upboxserver.v1.user.model.User;
import br.com.upboxserver.v1.user.service.UserService;
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

    User delete(String username) {
        return service.delete(username);
    }

    User update(String userToBeUpdated, User user) {
        return service.update(userToBeUpdated, user);
    }
}
