package com.example.HotelBookingApp.Room;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public RoomEntity addNewRoom(RoomEntity room){
        if(room == null){
            throw new IllegalArgumentException("Room cannot be null!");
        }
        return this.roomRepository.save(room);
    }


}
