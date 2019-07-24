package lits.jp.hotel.management.models;

import lombok.Data;

@Data
public class PlacesRequest {
  String provider;
  String type;
  Integer radius;
  Long lat;
  Long lng;
}
