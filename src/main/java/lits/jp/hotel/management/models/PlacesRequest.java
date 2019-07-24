package lits.jp.hotel.management.models;

import lombok.*;

@Data

public class PlacesRequest {
  String provider;
  String type;
  Integer radius;
  Double lat;
  Double lng;
}
