package ru.practikum.shareIt.item;

import java.util.List;

public interface ItemRepository {
    Item createItem(Item item);

    Item patchItem(Item item);

    Item deleteItem(Integer itemId, Integer userId);

    Item getItem(Integer itemId);

    List<Item> getItemsOfUser(Integer userId);

    Boolean checkItemContain(Integer itemId);

    List<Item> searchItems(String text);
}
