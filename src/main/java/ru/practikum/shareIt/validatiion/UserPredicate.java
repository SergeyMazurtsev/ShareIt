package ru.practikum.shareIt.validatiion;

import ru.practikum.shareIt.exception.ValidatorException;
import ru.practikum.shareIt.user.User;

import java.util.function.Predicate;

public interface UserPredicate extends Predicate<User> {
    ValidatorException errorObject();
}
