package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import thelocalspot.application.data.entity.CoordUser;

import java.util.List;
public interface CoordUserRepository extends JpaRepository<CoordUser, Long> {
    @Query("select c from CoordUser c " +
            "where c.id = :searchTerm")
    List<CoordUser> rememberId(Long searchTerm);

    @Query("select c from CoordUser c " +
            "where c.email = :searchTerm")
    List<CoordUser> rememberEmail(String searchTerm);
}
