package spring.security.example.spring_security.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import spring.security.example.spring_security.entyties.User;

//we also can use JpaRepository, but withh CrudRepository is enought
public interface UserRepository extends CrudRepository<User,Long>{

    //we need especify to return Set, because by default it return Lists
    Set<User> findAll();

    Optional<User> findByUsername(String username);
}
