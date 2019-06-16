package edu.mpp.bookstore2.web.converter;

import edu.mpp.bookstore2.core.model.User;
import edu.mpp.bookstore2.core.model.UserRole;
import edu.mpp.bookstore2.web.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverterBaseEntity<User, UserDto> {

    @Override
    public User convertDtoToModel(UserDto userDto) {
        UserRole myRole;
        if(userDto.getRole().equals("ADMIN"))
            myRole = UserRole.ADMIN;
        else
            myRole = UserRole.CLIENT;
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUsername())
                .password(userDto.getPassword())
                .userRole(myRole)
                .build();
        user.setId(userDto.getId());
        return user;
    }

    @Override
    public UserDto convertModelToDto(User user) {
        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUserName())
                .password(user.getPassword())
                .role(user.getUserRole().toString())
                .build();
        userDto.setId(user.getId());
        return userDto;
    }
}
