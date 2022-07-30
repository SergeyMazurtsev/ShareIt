package ru.practikum.shareIt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practikum.shareIt.checking.CheckModels;
import ru.practikum.shareIt.validatiion.UserPredicate;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practikum.shareIt.user.UserMapper.toUser;
import static ru.practikum.shareIt.user.UserMapper.toUserDto;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private CheckModels checkModels;
    private final List<UserPredicate> userValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CheckModels checkModels, List<UserPredicate> userValidator) {
        this.userRepository = userRepository;
        this.checkModels = checkModels;
        this.userValidator = userValidator;
    }

    public UserDto getUserById(Integer userId) {
        checkModels.checkUserContain(userId);
        return toUserDto(userRepository.getUser(userId));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User checkUser = toUser(userDto);
        userValidator.stream()
                .filter(v -> v.test(checkUser)).findFirst()
                .ifPresent(v -> {
                    throw v.errorObject();
                });
        return toUserDto(userRepository.createUser(checkUser));
    }

    @Override
    public UserDto patchUser(UserDto userDto, Integer userId) {
        checkModels.checkUserContain(userId);
        return toUserDto(userRepository.patchUser(toUser(userDto), userId));
    }

    @Override
    public UserDto deleteUser(Integer userId) {
        checkModels.checkUserContain(userId);
        return toUserDto(userRepository.deleteUser(userId));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.getUsers().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }
}
