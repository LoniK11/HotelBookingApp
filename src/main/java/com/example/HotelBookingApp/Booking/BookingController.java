package com.example.HotelBookingApp.Booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/{id}")
    public ResponseEntity<List<BookingEntity>> getBookingsByUserId(@PathVariable("id") int id){
        try{
            return ResponseEntity.ok(this.bookingService.getBookingsByClientId(id));
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookingById(@PathVariable("id") int id){
        try {
            this.bookingService.deleteBookingById(id);
            return ResponseEntity.ok("Booking deleted successfully!");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No booking found!");
        }
    }
}
