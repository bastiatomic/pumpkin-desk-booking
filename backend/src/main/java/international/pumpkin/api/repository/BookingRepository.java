package international.pumpkin.api.repository;


import international.pumpkin.api.model.Booking;
import international.pumpkin.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "INSERT INTO Booking (Desk_ID, BookingStartTime, BookingEndTime, BookedUSER) VALUES (:deskID, :startTime, :endTime, :bookedBy)", nativeQuery = true  )
    public void setFullBooking(@Param("deskID") final int deskID,
                               @Param("startTime") final Timestamp startTime,
                               @Param("endTime") final Timestamp endTime,
                               @Param("bookedBy") final int bookedBy);

    @Query(value = "DELETE FROM Booking WHERE Desk_ID = :deskID AND BookingStartTime = :startTime AND BookingEndTime = :endTime AND BookedUSER = :bookedBy", nativeQuery = true  )
    public void deleteFullBooking(@Param("deskID") final int deskID,
                               @Param("startTime") final Timestamp startTime,
                               @Param("endTime") final Timestamp endTime,
                               @Param("bookedBy") final int bookedBy);

    @Query(value = "SELECT * FROM Booking WHERE Desk_ID=:deskID AND BookingStartTime < :currentTime AND BookingEndTime > :currentTime", nativeQuery = true  )
    Booking getCurrentBookingByDeskID(@Param("deskID") final int deskID, @Param("currentTime") final Timestamp currentTime);

    @Query(value = "SELECT * FROM Booking WHERE Desk_ID=:deskID", nativeQuery = true)
    List<Booking> getAllDeskBookings(@Param("deskID") final int deskID);

    @Query(value = "SELECT * FROM Booking WHERE BookedUSER=:userID", nativeQuery = true)
    List<Booking> getAllUserBookings(@Param("userID") final int userID);

    @Query(value = "SELECT * FROM Booking WHERE BookingStartTime < :currentTime AND BookingEndTime > :currentTime", nativeQuery = true)
    List<Booking> getBookingData(@Param("currentTime") final Timestamp currentTime);


    @Query(value = "SELECT ID, Username FROM User WHERE ID =:userID", nativeQuery = true)
    List<String> getUser(@Param("userID") final int userID);

}
