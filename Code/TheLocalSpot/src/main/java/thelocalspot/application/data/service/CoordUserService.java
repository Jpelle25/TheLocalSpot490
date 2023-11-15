package thelocalspot.application.data.service;
import org.springframework.stereotype.Service;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.repository.CoordUserRepository;
import java.util.List;
@Service
public class CoordUserService {
    private final CoordUserRepository coordUserRepository;
    public CoordUserService(CoordUserRepository coordUserRepository) {
        this.coordUserRepository = coordUserRepository;
    }
    public void saveCordUser(CoordUser coordUser) {
        if (coordUser == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        coordUserRepository.save(coordUser);
    }
    public List<CoordUser> getCoordUserDetails(){
        return coordUserRepository.findAll();
    }
    public List<CoordUser> getCoordUserId(Long coordId){
        return coordUserRepository.rememberId(coordId);
    }
    public List<CoordUser> getCoordUserEmail(String email){
        return coordUserRepository.rememberEmail(email);
    }
}