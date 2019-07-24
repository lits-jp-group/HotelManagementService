package lits.jp.hotel.management.controller;

import java.io.IOException;
import java.util.List;
import lits.jp.hotel.management.models.PlacesRequest;
import lits.jp.hotel.management.places.PlacesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PlacesController {
  @Autowired PlacesService placesService;

  @RequestMapping("/places")
  List<String> getGooglePlaces(@RequestBody PlacesRequest request) throws IOException {

    log.info(request.toString());

    return placesService.getNearPlaces(request);
  }
}
