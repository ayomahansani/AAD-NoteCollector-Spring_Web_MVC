package lk.ijse.notecollectorspringmvc.dao;

import lk.ijse.notecollectorspringmvc.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // me interface eka data handle karanava kiyala kiyanna . bean ekak bavata path karanava
public interface UserDao extends JpaRepository<UserEntity,String> {

    // methanata CrudRepository kiyana interface ekath danna puluvan.
    // mokada crud methods tika enne CrudRepository interface eken.
    // JpaRepository eka CrudRepository eken extends kara;a thiyenne. e nisai api methana JpaRepository kiyala damme

}
