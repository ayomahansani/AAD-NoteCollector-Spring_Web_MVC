package lk.ijse.notecollectorspringmvc.dto.impl;

import lk.ijse.notecollectorspringmvc.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserStatus {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    private List<NoteDTO> notes; // eka user kenekta notes godai

}
