package ru.practikum.shareIt.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto {
    private int id;
    private String name;
    private String description;
    private Integer owner;
    private Boolean available;
    private Integer request;
}
