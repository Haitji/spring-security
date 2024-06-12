package spring.security.example.spring_security.services.implem;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.security.example.spring_security.entyties.User;
import spring.security.example.spring_security.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }
        User user = optionalUser.orElseThrow();

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .authorities(user.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()))
            .build();
    }

}
