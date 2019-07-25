package lits.jp.hotel.management.places;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import lits.jp.hotel.management.models.PlacesRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class GooglePlaces implements SearchPlaces {

  public static final String METHOD_NEARBY_SEARCH = "nearbysearch";
  public static final String ARRAY_RESULTS = "results"; // Array for results
  public static final String DOUBLE_LATITUDE = "lat"; // Latitude of place
  public static final String DOUBLE_LONGITUDE = "lng"; // Longitude of place
  public static final String OBJECT_GEOMETRY = "geometry"; // Geographic information
  public static final String OBJECT_LOCATION =
      "location"; // Contains latitude and longitude coordinates
  public static final String PLACE_TYPES = "types"; // Place type
  public static final String STRING_NAME = "name"; // The name of the place
  public static final String STRING_VICINITY = "vicinity"; // The address of the place

  @Override
  public List<String> getNearbyPlaces(
      List<String> places, PlacesRequest request, String googleUrl, String googleKey)
      throws IOException {

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity =
        restTemplate.getForEntity(createURI(request, googleUrl, googleKey), String.class);

    log.info(responseEntity.toString());
    log.info("============== end of response entity ===================");

    //    -------------------------- BEGIN SEND REQUEST DEPRECATED ------------------------//
    //    try {
    //      final HttpClient client = new DefaultHttpClient();
    //      final HttpUriRequest httpUriRequest = new HttpGet(url);
    //      final HttpResponse execute = client.execute(httpUriRequest);
    //      response = EntityUtils.toString(execute.getEntity());
    //    } catch (Exception e) {
    //      throw new StaffMemberException("IO Exception here"); // here it gives IO Exception
    //    }
    //    -------------------------- END SEND REQUEST ------------------------//
    //        // ----------------------- write RESPONSE to file ---------//
    //        try {
    //          FileWriter fw = new FileWriter("testout.txt");
    //          fw.write(response);
    //          fw.close();
    //        } catch (Exception e) {
    //          System.out.println(e);
    //        }
    //        log.info("Response successfully written to file testout.txt");
    //        //------------------ end write RESPONSE to file -----------------//

    //        // ------------------------ BEGIN READ FROM RESPONSEMOCK --------------
    //        ResponseMock rs = new ResponseMock();
    //        String contents = rs.giveMockedResponse();
    //        response = contents;
    //        // ------------------------ END READ FROM RESPONSEMOCK --------------

    JSONObject json = new JSONObject(responseEntity.getBody());
    JSONArray results = json.getJSONArray(ARRAY_RESULTS);
    for (int i = 0; i < results.length(); i++) {
      if (i <= results.length()) {
        JSONObject result = results.getJSONObject(i);
        JSONObject location = result.getJSONObject(OBJECT_GEOMETRY).getJSONObject(OBJECT_LOCATION);
        double lat2 = location.getDouble(DOUBLE_LATITUDE);
        double lon2 = location.getDouble(DOUBLE_LONGITUDE);
        String name = result.optString(STRING_NAME);
        String addr = result.optString(STRING_VICINITY, null);
        List<String> types = new ArrayList<>();
        JSONArray jsonTypes = result.optJSONArray(PLACE_TYPES);
        if (jsonTypes != null) {
          for (int a = 0; a < jsonTypes.length(); a++) {
            types.add(jsonTypes.getString(a));
          }
        }
        Place place = new Place(types, lat2, lon2, name, addr);
        places.add(place.toString());
      }
    }
    return places;
  }

  private URI createURI(PlacesRequest request, String googleUrl, String googleKey) {

    StringBuilder url = new StringBuilder();

    url.append(googleUrl).append(METHOD_NEARBY_SEARCH);
    url.append("/json?location=");
    url.append(request.getLat());
    url.append(",");
    url.append(request.getLng());
    url.append(new BasicNameValuePair("&radius", request.getRadius().toString()));
    url.append(new BasicNameValuePair("&type", request.getType()));
    url.append(new BasicNameValuePair("&key", googleKey));

    String finalurl = url.toString();
    finalurl = finalurl.replace('"', '}');
    finalurl = finalurl.replaceAll("[{}]", "");

    URI uri = null;
    try {
      uri = new URI(finalurl);
      log.info("My uri is " + uri);
    } catch (URISyntaxException e) {
      log.info("Error in URL" + e);
    }
    log.info(uri.toString());
    return uri;
  }
}
