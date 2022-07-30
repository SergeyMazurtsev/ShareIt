package ru.practikum.shareIt.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practikum.shareIt.checking.CheckModels;
import ru.practikum.shareIt.validatiion.ItemPredicate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practikum.shareIt.item.ItemMapper.toItem;
import static ru.practikum.shareIt.item.ItemMapper.toItemDto;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;
    private CheckModels checkModels;
    private final List<ItemPredicate> itemValidator;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CheckModels checkModels, List<ItemPredicate> itemPredicate) {
        this.itemRepository = itemRepository;
        this.checkModels = checkModels;
        this.itemValidator = itemPredicate;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto, Integer userId) {
        checkModels.checkUserContain(userId);
        itemDto.setOwner(userId);
        Item checkItem = toItem(itemDto);
        itemValidator.stream()
                .filter(i -> i.test(checkItem)).findFirst()
                .ifPresent(i -> {
                    throw i.errorObject();
                });
        return toItemDto(itemRepository.createItem(checkItem));
    }

    @Override
    public ItemDto patchItem(ItemDto itemDto, Integer itemId, Integer userId) {
        checkModels.checkUserContain(userId);
        checkModels.checkItemContain(itemId);
        Item item = toItem(itemDto);
        item.setId(itemId);
        item.setOwner(userId);
        return toItemDto(itemRepository.patchItem(item));
    }

    @Override
    public ItemDto deleteItem(Integer itemId, Integer userId) {
        checkModels.checkItemContain(itemId);
        return toItemDto(itemRepository.deleteItem(itemId, userId));
    }

    @Override
    public ItemDto getItem(Integer itemId) {
        checkModels.checkItemContain(itemId);
        return toItemDto(itemRepository.getItem(itemId));
    }

    @Override
    public List<ItemDto> getItemsOfUser(Integer userId) {
        checkModels.checkUserContain(userId);
        return itemRepository.getItemsOfUser(userId)
                .stream().map(ItemMapper::toItemDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        if (text == "") {
            return Collections.emptyList();
        }
        return itemRepository.searchItems(text.toLowerCase())
                .stream().map(ItemMapper::toItemDto).collect(Collectors.toList());
    }
}
