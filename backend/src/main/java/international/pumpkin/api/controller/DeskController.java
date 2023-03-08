package international.pumpkin.api.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import international.pumpkin.api.model.Desk;
import international.pumpkin.api.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/desk")
public class DeskController {

    private final DeskRepository deskRepository;

    @Autowired
    public DeskController(final DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }


    @GetMapping()
    public ResponseEntity<Desk> getDeskByID(@RequestParam(name = "deskID") int deskID) {

        try {
            return new ResponseEntity<>(deskRepository.getReferenceById(deskID), HttpStatus.OK);
        } catch (HttpMessageNotWritableException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/block")
    public ResponseEntity block(@RequestParam(name = "deskID") int deskID) {

        if (deskRepository.getReferenceById(deskID).isBlocked()) {
            //Tisch schon geblockt und soll nochmal geblockt werden
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            //Tisch frei und soll geblockt werden
            deskRepository.blocking(deskID);

/*            //logging in DeskUsage
            ZonedDateTime zdt = ZonedDateTime.now();
            deskRepository.blockLogging(deskID, zdt);*/
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/free")
    public ResponseEntity freeing(@RequestParam(name = "deskID") int deskID) {

        if (!deskRepository.getReferenceById(deskID).isBlocked()) {
            //Tisch schon frei und soll nochmal freigegeben werden
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            //Tisch geblockt und soll freigegeben werden
            deskRepository.unblocking(deskID);

/*            //logging in DeskUsage
            ZonedDateTime zdt = ZonedDateTime.now();
            deskRepository.unblockLogging(deskID, zdt);*/
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> status(@RequestParam(name = "deskID") int deskID) {

        if (deskRepository.getReferenceById(deskID).isBlocked()) {
            return new ResponseEntity<>(Collections.singletonMap("blocked", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("blocked", false), HttpStatus.OK);
        }
    }

    @GetMapping("/temperature")
    public ResponseEntity<String> setTemperature(@RequestParam(name = "temperature") float temperature,
                                                @RequestParam(name = "deskID") int deskID){
        Timestamp ts = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        deskRepository.setTemperature(deskID, ts, temperature);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/basedata")
    public ResponseEntity<List<Desk>> getBaseData(){
        return new ResponseEntity<>(deskRepository.getBaseData(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Das ist eine Teststelle\n", HttpStatus.FOUND);
    }
}
