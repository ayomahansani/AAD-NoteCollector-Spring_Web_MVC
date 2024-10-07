package lk.ijse.notecollectorspringmvc.service;

import lk.ijse.notecollectorspringmvc.dto.NoteStatus;
import lk.ijse.notecollectorspringmvc.dto.impl.NoteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    void saveNote(NoteDTO noteDTO);
    List<NoteDTO> getAllNotes();
    NoteStatus getNote(String noteId);
    void deleteNote(String noteId);
    void updateNote(String noteId, NoteDTO noteDTO);

}
