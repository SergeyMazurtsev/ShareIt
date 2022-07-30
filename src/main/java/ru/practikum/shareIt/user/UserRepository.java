package ru.practikum.shareIt.user;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    User patchUser(User user, Integer userId);

    User deleteUser(Integer userId);

    User getUser(Integer userId);

    List<User> getUsers();

    Boolean checkUserContain(Integer userId);
}
