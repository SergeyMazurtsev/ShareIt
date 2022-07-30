package ru.practikum.shareIt.user;

import org.springframework.stereotype.Component;
import ru.practikum.shareIt.exception.UserEmailException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepositoryImpl implements UserRepository {
    private Map<Integer, User> users = new HashMap<>();
    private Integer id = 0;

    @Override
    public User createUser(User user) {
        checkUserEmail(user.getEmail());
        user.setId(getNextId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User patchUser(User user, Integer userId) {
        checkUserEmail(user.getEmail());
        User findUser = users.get(userId);
        if (user.getName() != null) {
            findUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            checkUserEmail(user.getEmail());
            findUser.setEmail(user.getEmail());
        }
        return findUser;
    }

    @Override
    public User deleteUser(Integer userId) {
        return users.remove(userId);
    }

    @Override
    public User getUser(Integer userId) {
        return users.get(userId);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Boolean checkUserContain(Integer userId) {
        return users.containsKey(userId);
    }

    private Integer getNextId() {
        return ++id;
    }

    private void checkUserEmail(String email) {
        users.values().stream().map(User::getEmail)
                .filter(u -> u.equals(email)).findFirst()
                .ifPresent(e -> {
                    throw new UserEmailException("Пользователь с таким именем уже существует.");
                });
    }
}
