/*
package international.pumpkin.spring_api.controller;

import international.pumpkin.spring_api.model.Desk;
import international.pumpkin.spring_api.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class APIController {

//  TODO: alle api inputs für SQL santizen;

  private final DeskRepository deskRepository;

  @Autowired
  public APIController(final DeskRepository deskRepository) {
    this.deskRepository = deskRepository;
  }

  @GetMapping(/desk)
  public ResponseEntity<Desk> getAllDesks(@RequestParam(id = "id") final String id) {
    if (id != null) {
      return new ResponseEntity<>(deskRepository.getById(id), HttpStatus.OK);
    }
    return new ResponseEntity<>(deskRepository.getAll(), HttpStatus.OK);
  }

  // TODO: Annotation anpassen
  @GetMapping("/desk/block")
  public void setDeskBlock(@RequestParam(name = "deskID") String deskID) {

    int parsedDeskID = Integer.parseInt(deskID);
//    gehe zur datenbank und mache folgendes sql statement:
//    UPDATE DeskBaseData
//    SET Blocked = 1
//    WHERE ID = parsedDeskID
//


  }

// TODO: Annotation anpassen
  @GetMapping("/desk/release")
  public void setDeskRelease(@RequestParam(name = "deskID") String deskID) {

    int parsedDeskID = Integer.parseInt(deskID);
//    TODO:  gehe zur datenbank und mache folgendes sql statement:
//    UPDATE DeskBaseData
//    SET Blocked = 0
//    WHERE ID = parsedDeskID;
//

//    TODO:  Tabelle DeskUsage noch verwenden und eintrageungen mit Jonas absprechen
//    NEUE STRUKTUR!
  }



  // TODO: Annotation anpassen
  @GetMapping("/desk/temp")
  public void setTemperature(@RequestParam(name = "deskID") String deskID,
                             @RequestParam(name = "value") String temp) {

    int parsedDeskID = Integer.parseInt(deskID);
    double parsedTemp = Double.parseDouble(temp);
//    TODO:  gehe zur datenbank und mache folgendes sql statement:
//    UPDATE DeskBaseData
//    SET Temperatur = parsedTemp
//    WHERE ID = parsedDeskID;
  }



// frontend will diese daten
//  deskid, name, floor, blocked, equipment,
//  bookedByUserID, bookingStartTime, bookingEndTime
//  username



//  TODO: new user, daten an frontend, meldungen von controller, controller get buchungsstatus





  // TODO: Annotation anpassen
  @GetMapping("/auth")
  public void userAuthentification(@RequestParam(name = "userID") String userID,
                                   @RequestParam(name = "hash") String hash){

    int parsedUserID = Integer.parseInt(userID);
    String storedHash = ""; // TODO:  SQL Statement:
//    SELECT Password
//    FROM User
//    WHERE ID = userID

    if(storedHash.equals(hash)) {
      // return to caller: true
    } else {
      // return to caller false
    }
  }


  // TODO: Annotation anpassen
  @GetMapping("/desk/reserve")
  public void reserveDesk(@RequestParam(name = "userID") String userID,
                          @RequestParam(name = "deskID") String deskID,
//                          @RequestParam(name = "date") String date,
                          @RequestParam(name = "timeStart") String timeStart,
                          @RequestParam(name = "timeEnd") String timeEnd){

    int parsedUserID = Integer.parseInt(userID);
    int parsedDeskID = Integer.parseInt(deskID);
    // TODO: date und time werden noch geparsed
    Object parsedDate;
    Object parsedTimeStart;
    Object parsedTimeEnd;

//    TODO:  SQL Statement zum Eintragen der Reservierung
// INSERT INTO Booking (BookedBy(UserID), Desk_ID, BookingStartTime, BookingEndTime)
// VALUES ('parsedUserID', 'parsedDeskID', 'parsedTimeStart', 'parsedTimeEnd')
  }




}*/
