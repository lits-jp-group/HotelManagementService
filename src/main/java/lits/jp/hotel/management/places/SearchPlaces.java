package lits.jp.hotel.management.places;

import java.io.IOException;
import java.util.List;
import lits.jp.hotel.management.models.PlacesRequest;

public interface SearchPlaces {
  List<String> getNearbyPlaces(List<String> places, PlacesRequest request) throws IOException;
}
