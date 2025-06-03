package com.example.HotelBookingApp.Booking;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookingDTO {
    private int clientId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
