package com.example.HotelBookingApp.Booking;

import com.example.HotelBookingApp.Client.ClientEntity;
import com.example.HotelBookingApp.Room.RoomEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "room_id",nullable = false)
    private RoomEntity room;
}
