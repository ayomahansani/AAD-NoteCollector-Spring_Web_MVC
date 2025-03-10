package lk.ijse.notecollectorspringmvc.entity.impl;

import jakarta.persistence.*;
import lk.ijse.notecollectorspringmvc.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "note")
public class NoteEntity implements SuperEntity {

    @Id
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createdDate;
    private String priorityLevel;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false) // user kenek inna ba note ekak nathuava
    private UserEntity user;
}
