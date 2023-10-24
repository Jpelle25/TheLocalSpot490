package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thelocalspot.application.data.entity.CoordUser;

import java.util.List;
public interface CoordUserRepository extends JpaRepository<CoordUser, Long> {
}
