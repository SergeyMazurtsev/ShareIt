package ru.practikum.shareIt.booking;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    private int id;
    private Integer item;
    private LocalDateTime startRent;
    private LocalDateTime endRent;
    private Integer booker;
    private BookingStatus status;
    private String review;
}
