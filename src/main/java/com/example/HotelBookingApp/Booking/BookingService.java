package com.example.HotelBookingApp.Booking;

import com.example.HotelBookingApp.Client.ClientEntity;
import com.example.HotelBookingApp.Client.ClientRepository;
import com.example.HotelBookingApp.Room.RoomEntity;
import com.example.HotelBookingApp.Room.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;
    private RoomRepository roomRepository;

    public BookingService(
            BookingRepository bookingRepository,
            ClientRepository clientRepository,
            RoomRepository roomRepository
    ){
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
    }

    public BookingEntity addNewBooking(BookingDTO bookingDTO){
        if(bookingDTO.getCheckInDate().isAfter(bookingDTO.getCheckOutDate()) ||
         bookingDTO.getCheckInDate().equals(bookingDTO.getCheckOutDate())
        ){
            throw new IllegalArgumentException("Starting date cannot be after ending date!");
        }

        ClientEntity client = this.clientRepository.findById(bookingDTO.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Cannot find user!"));

        RoomEntity room = this.roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(() -> new NoSuchElementException("Cannot find room!"));

        List<BookingEntity> conflictBookingList = this.bookingRepository.
                getConflictingBookings(
                        bookingDTO.getRoomId(),
                        bookingDTO.getCheckInDate(),
                        bookingDTO.getCheckOutDate()
                );

        if(conflictBookingList.isEmpty()){
            BookingEntity booking = new BookingEntity();

            booking.setClient(client);
            booking.setRoom(room);
            booking.setCheckInDate(bookingDTO.getCheckInDate());
            booking.setCheckOutDate(bookingDTO.getCheckOutDate());
            long days = ChronoUnit.DAYS
                    .between(bookingDTO.getCheckInDate(),bookingDTO.getCheckOutDate());
            booking.setTotalPrice(days * room.getPricePerNight());

            return this.bookingRepository.save(booking);
        }else{
            throw new IllegalStateException("Room is already booked!");
        }

    }

    public List<BookingEntity> getAllBookings(){
        return this.bookingRepository.findAll();
    }

    public void deleteBookingById(int id){
        if(!this.bookingRepository.existsById(id)){
            throw new NoSuchElementException("No booking found!");
        }

        this.bookingRepository.deleteById(id);
    }
}
