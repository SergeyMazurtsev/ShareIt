package ru.practikum.shareIt.item;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto, Integer userId);

    ItemDto patchItem(ItemDto itemDto, Integer itemId, Integer userId);

    ItemDto deleteItem(Integer itemId, Integer userId);

    ItemDto getItem(Integer itemId);

    List<ItemDto> getItemsOfUser(Integer userId);

    List<ItemDto> searchItems(String text);
}
