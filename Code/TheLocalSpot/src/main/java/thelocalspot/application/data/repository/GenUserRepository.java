package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thelocalspot.application.data.entity.GenUser;

import java.util.List;

public interface GenUserRepository extends JpaRepository<GenUser, Long> {

}
