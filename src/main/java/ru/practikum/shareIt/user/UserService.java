package ru.practikum.shareIt.user;

import java.util.List;

public interface UserService {
    UserDto getUserById(Integer userId);

    UserDto createUser(UserDto user);

    UserDto patchUser(UserDto userDto, Integer userId);

    UserDto deleteUser(Integer userId);

    List<UserDto> getUsers();
}
