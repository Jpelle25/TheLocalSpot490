package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("select c from Place c " +
            "where c.hostId =  :searchTerm ")
    List<Place> search(Long searchTerm);
}
