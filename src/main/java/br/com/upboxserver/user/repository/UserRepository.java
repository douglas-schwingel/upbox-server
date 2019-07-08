package br.com.upboxserver.user.repository;

import br.com.upboxserver.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    User deleteByUsername(String username);
}
