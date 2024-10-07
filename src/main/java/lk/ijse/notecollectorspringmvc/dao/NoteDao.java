package lk.ijse.notecollectorspringmvc.dao;

import lk.ijse.notecollectorspringmvc.entity.impl.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<NoteEntity,String> {
}
