package lits.jp.hotel.management.places;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lits.jp.hotel.management.models.PlacesRequest;
import org.springframework.stereotype.Service;

@Service
public class PlacesService {

  public List<String> getNearPlaces(PlacesRequest request) throws IOException {
    List<String> responseListPlaces = new ArrayList<>();

    if (request.getProvider().equals("google")) {
      GooglePlaces googlePlaces = new GooglePlaces();
      googlePlaces.getNearbyPlaces(responseListPlaces, request);
    }
    return responseListPlaces;
  }
}
