package lits.jp.hotel.management.places;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lits.jp.hotel.management.models.PlacesRequest;
import org.springframework.stereotype.Service;

@Service
public class PlacesService {

  private String url;
  private String key;

  public PlacesService(GooglePlacesSettings settings) {
    this.url = settings.getApi().getGoogleUrl();
    this.key = settings.getApi().getGoogleKey();
  }

  public List<String> getNearPlaces(PlacesRequest request) throws IOException {
    List<String> responseListPlaces = new ArrayList<>();

    if (request.getProvider().equals("google")) {
      GooglePlaces googlePlaces = new GooglePlaces();
      googlePlaces.getNearbyPlaces(responseListPlaces, request, url, key);
    }
    return responseListPlaces;
  }
}
