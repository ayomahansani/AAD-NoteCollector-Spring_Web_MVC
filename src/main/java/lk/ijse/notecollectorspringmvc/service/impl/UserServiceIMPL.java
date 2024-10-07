package lk.ijse.notecollectorspringmvc.service.impl;

import lk.ijse.notecollectorspringmvc.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspringmvc.dao.UserDao;
import lk.ijse.notecollectorspringmvc.dto.UserStatus;
import lk.ijse.notecollectorspringmvc.dto.impl.UserDTO;
import lk.ijse.notecollectorspringmvc.entity.impl.UserEntity;
import lk.ijse.notecollectorspringmvc.exception.DataPersistException;
import lk.ijse.notecollectorspringmvc.exception.UserNotFoundException;
import lk.ijse.notecollectorspringmvc.service.UserService;
import lk.ijse.notecollectorspringmvc.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // use this annotation for converting to a bean
@Transactional
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity saveUserEntity = userDao.save(mapping.toUserEntity(userDTO)); // convert userDTO to userEntity
        if (saveUserEntity == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDao.findAll();
        return mapping.toUserDTOList(allUsers);
    }

    @Override
    public UserStatus getUser(String userId) {
        if(userDao.existsById(userId)){
            UserEntity selectedUserEntity = userDao.getReferenceById(userId);
            return mapping.toUserDTO(selectedUserEntity);
        }else {
            return new SelectedUserAndNoteErrorStatus(2, "User with id " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existedUser = userDao.findById(userId);

        if(!existedUser.isPresent()){
            throw new UserNotFoundException("User with id " + userId + " not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDao.findById(userId);

        if(tmpUser.isPresent()){
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }


}
