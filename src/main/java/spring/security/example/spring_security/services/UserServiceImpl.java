package spring.security.example.spring_security.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.security.example.spring_security.entyties.Role;
import spring.security.example.spring_security.entyties.User;
import spring.security.example.spring_security.entyties.Dto.UserDto;
import spring.security.example.spring_security.mappers.UserMapper;
import spring.security.example.spring_security.repositories.RoleRepository;
import spring.security.example.spring_security.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Transactional(readOnly = true)
    @Override
    public Set<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User registUser(UserDto userDto) {
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER");//We get a role from database to avoid duplication
        roles.add(role);
        User user = userMapper.userDtoToUser(userDto);
        user.setEnabled(true);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

}
