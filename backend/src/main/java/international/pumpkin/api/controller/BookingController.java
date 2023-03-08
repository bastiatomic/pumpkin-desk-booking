package international.pumpkin.api.controller;

import international.pumpkin.api.model.Booking;
import international.pumpkin.api.model.User;
import international.pumpkin.api.repository.BookingRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    public final BookingRepository bookingRepository;

    @Autowired
    public BookingController(final BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

/*    Returns Userdata
    @GetMapping()
    public ResponseEntity<List<String>> getBookingStatus(@RequestParam(name = "deskID")int deskID){
        Booking currentBookingAtDesk = bookingRepository.getCurrentBookingByDeskID(deskID, Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
        if (currentBookingAtDesk == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(bookingRepository.getUser(currentBookingAtDesk.getUserID()), HttpStatus.OK);
        }
    }*/

    @GetMapping()
    public ResponseEntity<Map<String, Boolean>> getBookingStatus(@RequestParam(name = "deskID")int deskID) {
        if (bookingRepository.getCurrentBookingByDeskID(deskID, Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime())) == null) {
            return new ResponseEntity<>(Collections.singletonMap("booked", false), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("booked", true), HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBooking(){
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/deskallres")
    public ResponseEntity<List<Booking>> getReservationsByDeskID(@RequestParam(name = "deskID")int deskID){
        return new ResponseEntity<>(bookingRepository.getAllDeskBookings(deskID),HttpStatus.OK);
    }

    @GetMapping("/userallres")
    public ResponseEntity<List<Booking>> getReservationsByUserID(@RequestParam(name = "userID")int userID){
        return new ResponseEntity<>(bookingRepository.getAllUserBookings(userID),HttpStatus.OK);
    }


    @GetMapping("/bookingdata")
    public ResponseEntity<List<Booking>> getBookingData(@RequestParam(name = "datetime") Timestamp timestamp){
        return new ResponseEntity<>(bookingRepository.getBookingData(timestamp), HttpStatus.OK);
    }

    @GetMapping("/res")
    public ResponseEntity fullBooking(@RequestParam(name = "deskID") int deskID,
                                                            @RequestParam(name = "startTime") Timestamp startTime,
                                                            @RequestParam(name = "endTime") Timestamp endTime,
                                                            @RequestParam(name = "bookedBy") int bookedBy){

        bookingRepository.setFullBooking(deskID, startTime, endTime, bookedBy);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity deleteBooking(@RequestParam(name = "deskID") int deskID,
                                          @RequestParam(name = "startTime") Timestamp startTime,
                                          @RequestParam(name = "endTime") Timestamp endTime,
                                          @RequestParam(name = "bookedBy") int bookedBy){
        bookingRepository.deleteFullBooking(deskID, startTime, endTime, bookedBy);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
