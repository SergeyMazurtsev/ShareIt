package ru.practikum.shareIt.item;

import org.springframework.stereotype.Component;
import ru.practikum.shareIt.exception.NotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ItemRepositoryImpl implements ItemRepository {
    private Map<Integer, Item> items = new HashMap<>();
    private Integer id = 0;

    @Override
    public Item createItem(Item item) {
        item.setId(getNextId());
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item patchItem(Item item) {
        Item findItem = items.get(item.getId());
        if (item.getOwner() != findItem.getOwner()) {
            throw new NotFoundException("Предмет не найден.");
        }
        if (item.getName() != null) {
            findItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            findItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            findItem.setAvailable(item.getAvailable());
        }
        return findItem;
    }

    @Override
    public Item deleteItem(Integer itemId, Integer userId) {
        Item item = items.get(itemId);
        if (item.getOwner() == userId) {
            items.remove(itemId);
            return item;
        } else {
            throw new NotFoundException("Предмет не найден.");
        }
    }

    @Override
    public Item getItem(Integer itemId) {
        return items.get(itemId);
    }

    @Override
    public List<Item> getItemsOfUser(Integer userId) {
        List<Item> findItems = items.values().stream().filter(item -> item.getOwner().equals(userId))
                .collect(Collectors.toList());
        if (!findItems.isEmpty()) {
            return findItems;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Boolean checkItemContain(Integer itemId) {
        return items.containsKey(itemId);
    }

    @Override
    public List<Item> searchItems(String text) {
        return items.values().stream()
                .filter(i -> (i.getName().toLowerCase().contains(text) || i.getDescription().toLowerCase().contains(text)))
                .filter(Item::getAvailable)
                .collect(Collectors.toList());
    }

    private Integer getNextId() {
        return ++id;
    }

}
