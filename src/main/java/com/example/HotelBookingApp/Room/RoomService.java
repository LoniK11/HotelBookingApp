package com.example.HotelBookingApp.Room;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    public void deleteRoomById(int id){
        if(!this.roomRepository.existsById(id)){
            throw new NoSuchElementException("No room found!");
        }

        this.roomRepository.deleteById(id);
    }

    public RoomEntity updateRoomById(int id, RoomEntity updatedRoom){

        RoomEntity room = this.roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No room found!"));

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setRoomType(updatedRoom.getRoomType());
        room.setPricePerNight(updatedRoom.getPricePerNight());

        return this.roomRepository.save(room);
    }

    public Map<LocalDate, List<RoomEntity>> getAllFreeRooms(LocalDate startDate, LocalDate endDate){
        Map<LocalDate, List<RoomEntity>> freeRoomPerDay = new TreeMap<>();

        LocalDate currentDate = startDate;
        while(!currentDate.isAfter(endDate)){
            freeRoomPerDay.put(
                    currentDate,
                    this.roomRepository.getAllFreeRooms(currentDate)
            );
            currentDate = currentDate.plusDays(1);
        }

        return freeRoomPerDay;
    }
}
