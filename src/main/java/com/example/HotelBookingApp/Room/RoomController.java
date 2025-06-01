package com.example.HotelBookingApp.Room;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/{id}")
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable("id") int id){
        try{
            RoomEntity room = this.roomService.getRoomById(id);
            return ResponseEntity.ok(room);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomById(@PathVariable("id") int id){
        try{
            this.roomService.deleteRoomById(id);
            return ResponseEntity.ok("Room deleted successfully!");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoomById(@PathVariable("id") int id, @RequestBody() RoomEntity room){
        try{
            RoomEntity newRoom = this.roomService.updateRoomById(id,room);
            return ResponseEntity.ok("Room updated successfully!");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found!");
        }
    }
}
