package spring.security.example.spring_security.services;


import java.util.Set;

import spring.security.example.spring_security.entyties.User;
import spring.security.example.spring_security.entyties.Dto.UserDto;

public interface UserService {

    Set<User> getAllUsers();

    User registUser(UserDto userDto);
}
