package thelocalspot.application.data.service;

import org.springframework.stereotype.Service;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.entity.Place;
import thelocalspot.application.data.repository.HostRepository;

import java.util.List;

@Service
public class HostService {

    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) { this.hostRepository = hostRepository; }

    public void saveHost(Host host){
        if (host == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        hostRepository.save(host);
    }

    public List<Host> getUserDetails(){

        return hostRepository.findAll();
    }

    public List<Host> findAllHosts(){
        return hostRepository.findAll();
    }
}
