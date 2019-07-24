package lits.jp.hotel.management.places;

import lits.jp.hotel.management.places.GooglePlaces;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlacesService {

    public List<String> getNearPlaces (String type, Integer radius) throws IOException {
        GooglePlaces googlePlaces = new GooglePlaces();
        List <String> listPlaces = new ArrayList<>();

        googlePlaces.getNearbyPlaces(listPlaces, 49.8392205,24.0288738, radius, type);

        return listPlaces;
    }

}


