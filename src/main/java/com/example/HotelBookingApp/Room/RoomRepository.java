package com.example.HotelBookingApp.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query("SELECT r FROM RoomEntity r WHERE r.roomId NOT IN " +
            "(SELECT b.room.roomId FROM BookingEntity b WHERE " +
            "b.checkInDate <= :currentDate AND b.checkOutDate > :currentDate)")
    List<RoomEntity> getAllFreeRooms(@Param("currentDate") LocalDate currentDate);
}
