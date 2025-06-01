package com.example.HotelBookingApp.Room;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

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

    public List<RoomEntity> getAllRooms(){
        return this.roomRepository.findAll();
    }

    public RoomEntity getRoomById(int id){
        return this.roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No room found!"));
    }

}
