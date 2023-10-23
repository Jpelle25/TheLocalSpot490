package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thelocalspot.application.data.entity.GenUser;

import java.util.List;

public interface GenUserRepository extends JpaRepository<GenUser, Long> {

    @Query("select c from GenUser c " +
            "where c.email =  :searchTerm ")
    List<GenUser> search(String searchTerm);

}
