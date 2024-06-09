package spring.security.example.spring_security.repositories;

import org.springframework.data.repository.CrudRepository;

import spring.security.example.spring_security.entyties.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

    Role findByName(String name);

}
