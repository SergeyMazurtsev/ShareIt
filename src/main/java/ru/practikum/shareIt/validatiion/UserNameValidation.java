package ru.practikum.shareIt.validatiion;

import org.springframework.stereotype.Service;
import ru.practikum.shareIt.exception.ValidatorException;
import ru.practikum.shareIt.user.User;

@Service
public class UserNameValidation implements UserPredicate {
    @Override
    public ValidatorException errorObject() {
        return new ValidatorException("Имя пользователя отсутствует или пустое.");
    }

    @Override
    public boolean test(User user) {
        return (user.getName() == "") || (user.getName() == null);
    }
}
