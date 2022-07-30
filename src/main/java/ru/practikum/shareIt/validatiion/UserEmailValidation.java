package ru.practikum.shareIt.validatiion;

import org.springframework.stereotype.Service;
import ru.practikum.shareIt.exception.ValidatorException;
import ru.practikum.shareIt.user.User;

@Service
public class UserEmailValidation implements UserPredicate {
    @Override
    public ValidatorException errorObject() {
        return new ValidatorException("Поле email не должно быть пустым.");
    }

    @Override
    public boolean test(User user) {
        return (user.getEmail() == null) || (user.getEmail() == "") || (!user.getEmail().contains("@"));
    }
}
