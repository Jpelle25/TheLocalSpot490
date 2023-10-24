package thelocalspot.application.data.service;

import org.springframework.stereotype.Service;
import thelocalspot.application.data.entity.Place;
import thelocalspot.application.data.repository.PlaceRepository;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) { this.placeRepository = placeRepository; }

    public void savePlace(Place place){
        if (place == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        placeRepository.save(place);
    }
}
