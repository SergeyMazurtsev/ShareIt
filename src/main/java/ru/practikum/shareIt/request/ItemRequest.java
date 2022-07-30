package ru.practikum.shareIt.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemRequest {
    private int id;
    private String description;
    private Integer userRequest;
    private LocalDateTime created;
}
