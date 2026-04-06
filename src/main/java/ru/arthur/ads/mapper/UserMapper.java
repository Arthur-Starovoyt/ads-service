package ru.arthur.ads.mapper;

import org.springframework.stereotype.Component;
import ru.arthur.ads.dto.Register;
import ru.arthur.ads.dto.UpdateUser;
import ru.arthur.ads.dto.User;
import ru.arthur.ads.entity.UserEntity;

@Component
public class UserMapper {

    public User toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPhone(entity.getPhone());
        user.setRole(entity.getRole());
        user.setImage(entity.getImage());
        return user;
    }

    public UserEntity fromRegisterDto(Register register) {
        if (register == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setEmail(register.getUsername());
        entity.setPassword(register.getPassword());
        entity.setFirstName(register.getFirstName());
        entity.setLastName(register.getLastName());
        entity.setPhone(register.getPhone());
        entity.setRole(register.getRole().name());
        return entity;
    }

    public void updateUserFields(UpdateUser dto, UserEntity entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
    }
}