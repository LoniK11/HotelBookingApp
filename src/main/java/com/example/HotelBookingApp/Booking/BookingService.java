package com.example.HotelBookingApp.Booking;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    public List<BookingEntity> getAllBookings(){
        return this.bookingRepository.findAll();
    }
}
