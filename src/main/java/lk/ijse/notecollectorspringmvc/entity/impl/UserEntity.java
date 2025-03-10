package lk.ijse.notecollectorspringmvc.entity.impl;

import jakarta.persistence.*;
import lk.ijse.notecollectorspringmvc.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity implements SuperEntity {

    @Id
    private String userId;

    private String firstName;
    private String lastName;

    @Column(unique = true) // email eka unique venne one
    private String email;

    private String password;

    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;

    @OneToMany(mappedBy = "user")
    private List<NoteEntity> notes;
}
