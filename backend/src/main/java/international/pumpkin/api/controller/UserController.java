package international.pumpkin.api.controller;


import international.pumpkin.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/webauth")
    public ResponseEntity<Map<String, Boolean>> checkWebAuthenticity(@RequestParam(name = "userID") int userID,
                                                                     @RequestParam(name = "pwHash") String pwHash) {

        if (userRepository.getReferenceById(userID).getPwHash().equals(pwHash)) {
            return new ResponseEntity<>(Collections.singletonMap("validation", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("validation", false), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/colorauth")
    public ResponseEntity<Map<String, Boolean>> checkColorAuthenticity(@RequestParam(name = "userID") int userID,
                                                                       @RequestParam(name = "colorHash") String colorHash){
        if (userRepository.getReferenceById(userID).getColorHash().equals(colorHash)){
            return new ResponseEntity<>(Collections.singletonMap("validation", true), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Collections.singletonMap("validation", false), HttpStatus.UNAUTHORIZED);
        }
    }




    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Das ist eine Teststelle\n", HttpStatus.FOUND);
    }
}
