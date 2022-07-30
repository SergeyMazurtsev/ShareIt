package ru.practikum.shareIt.checking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practikum.shareIt.exception.NotFoundException;
import ru.practikum.shareIt.item.ItemRepository;
import ru.practikum.shareIt.user.UserRepository;

@Service
public class CheckModels {
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @Autowired
    public CheckModels(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public void checkUserContain(Integer userId) {
        if (!userRepository.checkUserContain(userId)) {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public void checkItemContain(Integer itemId) {
        if (!itemRepository.checkItemContain(itemId)) {
            throw new NotFoundException("Предмет не найден.");
        }
    }
}
