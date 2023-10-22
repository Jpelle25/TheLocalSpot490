package thelocalspot.application.data.service;

import org.springframework.stereotype.Service;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.repository.GenUserRepository;

import java.util.List;

@Service
public class GenUserService {

    private final GenUserRepository genUserRepository;

    public GenUserService(GenUserRepository genUserRepository) {
        this.genUserRepository = genUserRepository;
    }

    public void saveUser(GenUser genUser) {
        if (genUser == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        genUserRepository.save(genUser);
    }

    public List<GenUser> getUserDetails(){

        return genUserRepository.findAll();
    }
}
