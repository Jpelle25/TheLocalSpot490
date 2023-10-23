package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thelocalspot.application.data.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
