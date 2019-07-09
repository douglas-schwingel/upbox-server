package br.com.upboxserver.v1.user.facade;

import br.com.upboxserver.v1.user.model.User;
import br.com.upboxserver.v1.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    private UserService service;

    public UserFacade(UserService service) {
        this.service = service;
    }

    public User saveUser(User user) {
        return service.saveUser(user);
    }

    public User find(String username) {
        return service.find(username);
    }

    public Long delete(String username) {
        return service.delete(username);
    }

    public User update(User user) {
        return service.update(user);
    }
}
