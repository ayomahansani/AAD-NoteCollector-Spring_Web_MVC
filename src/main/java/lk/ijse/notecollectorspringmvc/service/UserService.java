package lk.ijse.notecollectorspringmvc.service;

import lk.ijse.notecollectorspringmvc.dto.UserStatus;
import lk.ijse.notecollectorspringmvc.dto.impl.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserStatus getUser(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO userDTO);
}
