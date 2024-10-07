package lk.ijse.notecollectorspringmvc.controller;

import lk.ijse.notecollectorspringmvc.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspringmvc.dto.NoteStatus;
import lk.ijse.notecollectorspringmvc.dto.impl.NoteDTO;
import lk.ijse.notecollectorspringmvc.exception.DataPersistException;
import lk.ijse.notecollectorspringmvc.exception.NoteNotFoundException;
import lk.ijse.notecollectorspringmvc.service.NoteService;
import lk.ijse.notecollectorspringmvc.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // http request response handle karanna puluvan mattamata genava
@RequestMapping("api/v1/notes")
public class NoteController {

    // json mime type eke NoteDTO object ekak thama request eken enne.
    // ethakota enne json ekak . eeka convert karanna one java object ekakata => serialization.
    // requset body eken ena json eka alla ganna thama "consumes" danne.
    // server eka paththen client ta yawanneth json ekak kiyala kiyanna thama "produce" danne.
    // e vagema eka alla ganna NoteDTO noteDto parameter ekak hadanava "@RequestBody" keyword eka use karala.
    // end points 5k thiyenava me class eke . end points same venava CRUD operations valata.

    @Autowired // dependency injection (field injection) -> interface ekak thama inject karanne
    private NoteService noteService;


    // ----------- SAVE NOTE -----------
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNote(@RequestBody NoteDTO noteDTO) { // @RequestBody - url eke body eke ena data alla ganna

        // before dependency injection

        /*NoteServiceIMPL noteServiceIMPL = new NoteServiceIMPL();
        noteDTO.setNoteId(AppUtil.generateNoteId());
        noteServiceIMPL.saveNote(noteDTO);
        return noteDTO;*/


        // after dependency injection

        try {
            noteService.saveNote(noteDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // ----------- GET A NOTE -----------
    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteStatus getSelectedNote(@PathVariable ("noteId") String noteId){
        if(!RegexProcess.noteIdMatcher(noteId)){
            return new SelectedUserAndNoteErrorStatus(1, "Note ID is not valid");
        }
        return noteService.getNote(noteId);
    }


    // ----------- GET ALL NOTES -----------
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes() {

        return noteService.getAllNotes();
    }


    // ----------- DELETE NOTE -----------
    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable ("noteId") String noteId) {

        try {
            if(!RegexProcess.noteIdMatcher(noteId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.deleteNote(noteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoteNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ----------- UPDATE USER -----------
    @PutMapping(value = "/{noteId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO updatedNoteDTO) {
        try {
            if(!RegexProcess.noteIdMatcher(noteId) || updatedNoteDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.updateNote(noteId, updatedNoteDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
