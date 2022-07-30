package ru.practikum.shareIt.validatiion;

import org.springframework.stereotype.Service;
import ru.practikum.shareIt.exception.ValidatorException;
import ru.practikum.shareIt.item.Item;

@Service
public class ItemDescriptionValidation implements ItemPredicate {
    @Override
    public ValidatorException errorObject() {
        return new ValidatorException("Поле описания не может быть пустым или отсутствовать.");
    }

    @Override
    public boolean test(Item item) {
        return (item.getDescription() == "") || (item.getDescription() == null);
    }
}
