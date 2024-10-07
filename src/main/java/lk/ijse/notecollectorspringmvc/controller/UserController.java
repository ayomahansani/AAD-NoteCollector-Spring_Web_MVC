package lk.ijse.notecollectorspringmvc.controller;

import lk.ijse.notecollectorspringmvc.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspringmvc.dto.UserStatus;
import lk.ijse.notecollectorspringmvc.dto.impl.UserDTO;
import lk.ijse.notecollectorspringmvc.exception.DataPersistException;
import lk.ijse.notecollectorspringmvc.exception.UserNotFoundException;
import lk.ijse.notecollectorspringmvc.service.UserService;
import lk.ijse.notecollectorspringmvc.util.AppUtil;
import lk.ijse.notecollectorspringmvc.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController // http request response handle karanna puluvan mattamata genava
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired // dependency injection (field injection) -> interface ekak thama inject karanawa runtime ekedi
    private UserService userService;


    // ----------- SAVE USER -----------
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // postman eken MultipartFile type eken ne enne
    public ResponseEntity<Void> saveUser(@RequestPart("firstName") String firstName, @RequestPart("lastName") String lastName, @RequestPart("email") String email, @RequestPart("password") String password, @RequestPart("profilePic") MultipartFile profilePic) {

        // profilePic -----> Base64
        String base64ProfilePic = "";

        try {

            // UserId generate
            String userId = AppUtil.generateUserId();

            byte[] bytesProPic = profilePic.getBytes(); // ena profile picture eke byte collection eka ganna onee
            base64ProfilePic = AppUtil.profilePicToBase64(bytesProPic);

            // Build the Object ( api postman eken ewanne UserDTO ekak nemene. e nisai methana UserDTO ekak hadanne )
            UserDTO userDTO = new UserDTO();

            userDTO.setUserId(userId);
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setProfilePic(base64ProfilePic);

            userService.saveUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // ----------- GET A USER -----------
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getSelectedUser(@PathVariable("userId") String userId) {

        if(!RegexProcess.userIdMatcher(userId)){
            return new SelectedUserAndNoteErrorStatus(1, "User ID is not valid");
        }
        return userService.getUser(userId);

    }


    // ----------- GET ALL USERS -----------
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    // ----------- DELETE USER -----------
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId){

        try {

            if(!RegexProcess.userIdMatcher(userId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // ----------- UPDATE USER -----------
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{userId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUser(@RequestPart("firstName") String firstName, @RequestPart("lastName") String lastName, @RequestPart("email") String email, @RequestPart("password") String password, @RequestPart("profilePic") MultipartFile profilePic, @PathVariable ("userId") String userId){

        // profilePic -----> Base64
        String base64ProfilePic = "";

        try {
            byte[] bytesProPic = profilePic.getBytes(); // ena profile picture eke byte collection eka ganna onee
            base64ProfilePic = AppUtil.profilePicToBase64(bytesProPic);

            // Build the Object ( api postman eken ewanne UserDTO ekak nemene. e nisai methana UserDTO ekak hadanne )
            UserDTO userDTO = new UserDTO();

            userDTO.setUserId(userId);
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setProfilePic(base64ProfilePic);

            userService.updateUser(userId, userDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
