package com.example.HotelBookingApp.Room;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<String> addNewRoom(@RequestBody() RoomEntity room){
        try{
            RoomEntity createdRoom = this.roomService.addNewRoom(room);
            return ResponseEntity.ok("Room added successfully");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<RoomEntity> getAllRooms(){
        return this.roomService.getAllRooms();
    }
}
