package com.example.HotelBookingApp.Booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingEntity> addNewBooking(
            @RequestBody() BookingDTO bookingDTO
    ){
        try {
            BookingEntity booking = this.bookingService
                    .addNewBooking(bookingDTO);
            return ResponseEntity.ok(booking);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public List<BookingEntity> getAllBookings(){
        return this.bookingService.getAllBookings();
    }
}
