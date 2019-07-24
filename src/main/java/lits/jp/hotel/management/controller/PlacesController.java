package lits.jp.hotel.management.controller;

import java.io.IOException;
import java.util.List;
import lits.jp.hotel.management.models.PlacesRequest;
import lits.jp.hotel.management.places.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlacesController {
  @Autowired PlacesService placesService;

  @RequestMapping("/places")
  List<String> getGooglePlaces(@RequestBody PlacesRequest request) throws IOException {

    return placesService.getNearPlaces(request);
  }
}
