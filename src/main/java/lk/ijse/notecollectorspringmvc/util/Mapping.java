package lk.ijse.notecollectorspringmvc.util;

import lk.ijse.notecollectorspringmvc.dto.impl.NoteDTO;
import lk.ijse.notecollectorspringmvc.dto.impl.UserDTO;
import lk.ijse.notecollectorspringmvc.entity.impl.NoteEntity;
import lk.ijse.notecollectorspringmvc.entity.impl.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper; //model mapper eka inject kara gannava

    // =========== For user mapping ===========

    //for converting userDto to userEntity
    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    //for converting userEntity to userDto
    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    //for converting userEntityList to userDtoList
    public List<UserDTO> toUserDTOList(List<UserEntity> userEntityList) {
        return modelMapper.map(userEntityList, new TypeToken<List<UserDTO>>() {}.getType());
    }



    // =========== For note mapping ===========

    //for converting noteDto to noteEntity
    public NoteEntity toNoteEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, NoteEntity.class);
    }

    //for converting noteEntity to noteDto
    public NoteDTO toNoteDTO(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDTO.class);
    }

    //for converting noteEntityList to noteDtoList
    public List<NoteDTO> toNoteDTOList(List<NoteEntity> noteEntityList) {
        return modelMapper.map(noteEntityList, new TypeToken<List<NoteDTO>>() {}.getType());
    }

}
