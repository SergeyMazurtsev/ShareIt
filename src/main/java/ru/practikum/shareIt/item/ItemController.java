package ru.practikum.shareIt.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return ResponseEntity.ok(itemService.createItem(itemDto, userId));
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<?> patchItem(@RequestBody ItemDto itemDto, @PathVariable Integer itemId,
                                       @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return ResponseEntity.ok(itemService.patchItem(itemDto, itemId, userId));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer itemId,
                                        @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return ResponseEntity.ok(itemService.deleteItem(itemId, userId));
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<?> getItem(@PathVariable Integer itemId) {
        return ResponseEntity.ok(itemService.getItem(itemId));
    }

    @GetMapping("/items")
    public ResponseEntity<?> getItemsOfUser(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return ResponseEntity.ok(itemService.getItemsOfUser(userId));
    }

    @GetMapping("/items/search")
    public ResponseEntity<?> searchItems(@RequestParam String text) {
        return ResponseEntity.ok(itemService.searchItems(text));
    }
}
