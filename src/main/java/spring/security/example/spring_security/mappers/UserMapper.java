package spring.security.example.spring_security.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import spring.security.example.spring_security.entyties.User;
import spring.security.example.spring_security.entyties.Dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto usertToDto(User user);

    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User userDtoToUser(UserDto userDto);
}
