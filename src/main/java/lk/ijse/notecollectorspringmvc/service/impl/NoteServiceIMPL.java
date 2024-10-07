package lk.ijse.notecollectorspringmvc.service.impl;

import lk.ijse.notecollectorspringmvc.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspringmvc.dao.NoteDao;
import lk.ijse.notecollectorspringmvc.dto.NoteStatus;
import lk.ijse.notecollectorspringmvc.dto.impl.NoteDTO;
import lk.ijse.notecollectorspringmvc.entity.impl.NoteEntity;
import lk.ijse.notecollectorspringmvc.exception.DataPersistException;
import lk.ijse.notecollectorspringmvc.exception.NoteNotFoundException;
import lk.ijse.notecollectorspringmvc.service.NoteService;
import lk.ijse.notecollectorspringmvc.util.AppUtil;
import lk.ijse.notecollectorspringmvc.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // use this annotation for converting to a bean
@Transactional
public class NoteServiceIMPL implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private Mapping notesMapping;


    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());

        System.out.println("Generated note ID: " + noteDTO.getNoteId());

        NoteEntity savedNoteEntity = noteDao.save(notesMapping.toNoteEntity(noteDTO));
        if (savedNoteEntity == null) {
            throw new DataPersistException("Note not saved");
        }
    }


    @Override
    public List<NoteDTO> getAllNotes() {
        return notesMapping.toNoteDTOList(noteDao.findAll());
    }

    @Override
    public NoteStatus getNote(String noteId) {
        if(noteDao.existsById(noteId)){
            NoteEntity selectedNoteEntity = noteDao.getReferenceById(noteId);
            return notesMapping.toNoteDTO(selectedNoteEntity);
        } else {
            return new SelectedUserAndNoteErrorStatus(2, "Selected note not found");
        }
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> foundNoteEntity = noteDao.findById(noteId);
        if(!foundNoteEntity.isPresent()){
            throw new NoteNotFoundException("Note not found");
        } else {
            noteDao.deleteById(noteId);
        }
    }

    @Override
    public void updateNote(String noteId, NoteDTO noteDTO) {
        Optional<NoteEntity> findNote = noteDao.findById(noteId);
        if (!findNote.isPresent()) {
            throw new NoteNotFoundException("Note not found");
        }else {
            findNote.get().setNoteTitle(noteDTO.getNoteTitle());
            findNote.get().setNoteDesc(noteDTO.getNoteDesc());
            findNote.get().setCreatedDate(noteDTO.getCreatedDate());
            findNote.get().setPriorityLevel(noteDTO.getPriorityLevel());
        }
    }
}




/*

// just for test to get all notes

@Service
public class NoteServiceIMPL implements NoteService{

    private static List<NoteDTO> noteDTOList = new ArrayList<>();

    NoteServiceIMPL(){
        noteDTOList.add(new NoteDTO("Note-2", "Python", "PythonDesc", "2024-09-14", "P-2", "2"));
        noteDTOList.add(new NoteDTO("Note-3", "JS", "JSDesc", "2024-09-12", "P-3", "4"));
        noteDTOList.add(new NoteDTO("Note-4", "Kotlin", "KotlinDesc", "2024-09-15", "P-2", "1"));
        noteDTOList.add(new NoteDTO("Note-5", "Ts", "TsDesc", "2024-09-10", "P-4", "2"));
    }

    @Override
    public NoteDTO saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());
        noteDTOList.add(noteDTO);
        return noteDTO;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteDTOList;
    }

}
*/

