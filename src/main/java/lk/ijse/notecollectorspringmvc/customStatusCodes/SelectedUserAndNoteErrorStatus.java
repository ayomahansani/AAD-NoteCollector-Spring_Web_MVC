package lk.ijse.notecollectorspringmvc.customStatusCodes;

import lk.ijse.notecollectorspringmvc.dto.NoteStatus;
import lk.ijse.notecollectorspringmvc.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserAndNoteErrorStatus implements UserStatus, NoteStatus {

    private int statusCode;
    private String statusMessage;
}
