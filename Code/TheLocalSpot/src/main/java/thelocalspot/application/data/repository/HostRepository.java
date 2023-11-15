package thelocalspot.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thelocalspot.application.data.entity.Host;

public interface HostRepository extends JpaRepository<Host, Long> {

}
