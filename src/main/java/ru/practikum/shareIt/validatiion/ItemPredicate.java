package ru.practikum.shareIt.validatiion;

import ru.practikum.shareIt.exception.ValidatorException;
import ru.practikum.shareIt.item.Item;

import java.util.function.Predicate;

public interface ItemPredicate extends Predicate<Item> {
    ValidatorException errorObject();
}
