package ru.practikum.shareIt.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private int id;
    private String name;
    private String description;
    private Integer owner;
    private Boolean available;
    private Integer request;
}
