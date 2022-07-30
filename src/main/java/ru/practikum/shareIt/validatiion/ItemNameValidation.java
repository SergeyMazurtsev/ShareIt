package ru.practikum.shareIt.validatiion;

import org.springframework.stereotype.Service;
import ru.practikum.shareIt.exception.ValidatorException;
import ru.practikum.shareIt.item.Item;

@Service
public class ItemNameValidation implements ItemPredicate {
    @Override
    public ValidatorException errorObject() {
        return new ValidatorException("Поле названия предмета не может быть пустым или отсутствовать.");
    }

    @Override
    public boolean test(Item item) {
        return (item.getName() == "") || (item.getName() == null);
    }
}
