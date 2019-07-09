package br.com.upboxserver.v1.user.repository;

import br.com.upboxserver.v1.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    Long deleteByUsername(String username);
}
