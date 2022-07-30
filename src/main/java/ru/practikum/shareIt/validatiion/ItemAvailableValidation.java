package ru.practikum.shareIt.validatiion;

import org.springframework.stereotype.Service;
import ru.practikum.shareIt.exception.ValidatorException;
import ru.practikum.shareIt.item.Item;

@Service
public class ItemAvailableValidation implements ItemPredicate {
    @Override
    public ValidatorException errorObject() {
        return new ValidatorException("Поле доступности предмета не может быть пустым.");
    }

    @Override
    public boolean test(Item item) {
        return item.getAvailable() == null;
    }
}
