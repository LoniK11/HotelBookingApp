package com.example.HotelBookingApp.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    @Query("SELECT b FROM BookingEntity b WHERE b.room.roomId = :roomId AND " +
            "b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    public List<BookingEntity> getConflictingBookings(
            @Param("roomId") int roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

}
