package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.places.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/g")
public class GoogleController {
    @Autowired
    PlacesService placesService;

    @PostMapping (value = "/{q}")
    List<String> getGooglePlaces (@RequestBody String type, @PathVariable Integer q) throws IOException {

        return placesService.getNearPlaces(type, q);
    }
}
