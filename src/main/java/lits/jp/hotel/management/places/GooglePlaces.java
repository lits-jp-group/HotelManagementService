package lits.jp.hotel.management.places;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import lits.jp.hotel.management.exceptions.StaffMemberException;
import lits.jp.hotel.management.models.PlacesRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.websocket.server.UriTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
// @PropertySource("classpath:application.properties")
public class GooglePlaces implements SearchPlaces {
  // # TODO importing below value
  //  @Value("${google.url}")
  //  public static String pathApi;

  public static final String pathApi = "https://maps.googleapis.com/maps/api/place/";
  public static final String METHOD_NEARBY_SEARCH = "nearbysearch";
  public static final String ARRAY_RESULTS = "results"; // Array for results
  public static final String DOUBLE_LATITUDE = "lat"; // Latitude of place
  public static final String DOUBLE_LONGITUDE = "lng"; // Longitude of place
  public static final String OBJECT_GEOMETRY = "geometry"; // Geographic information
  public static final String OBJECT_LOCATION =
      "location"; // Contains latitude and longitude coordinates
  public static final String STRING_NAME = "name"; // The name of the place
  public static final String STRING_VICINITY = "vicinity"; // The address of the place
  public static final Double LAT= 49.83826;
  public static final Double LNG = 24.02324;

  private String apiKey = "AIzaSyD-PwiwfuC70H1ZFfqPkX3Wmdm6NCbxxXc";

  @Autowired private Environment environment;

  @Override
  public List<String> getNearbyPlaces(List<String> places, PlacesRequest request)
      throws IOException {

    log.info(request.toString());
    String type = request.getType();
    Integer radius = request.getRadius();
//    Double lat = request.getLat();
//    Double lng = request.getLng();
    Double lat = LAT;
    Double lng = LNG;

    log.info(lat.toString());
    log.info(lng.toString());
    String url = createURL(lat, lng, radius, type);

    String response;

    URI uri =null;
    try {
      uri = new URI(url);
      log.info("My uri is "+ uri);
    } catch (URISyntaxException e) {
      log.info("Error in URL" + e);
    }

    RestTemplate restTemplate = new RestTemplate();

    try {
      RequestEntity<?> RestTemplateRequest = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
      ResponseEntity<List> exchange = restTemplate.exchange(RestTemplateRequest, List.class);
      places = exchange.getBody();
    } catch(Exception e){
    }

    log.info(places.toString());
    return places;


//    public Weather getWeather(String city){
//      Weather weather = null;
//      URI url = new UriTemplate(this.apiUrl).expand(city, this.apiKey);
//
//      weather = invoke(url, Weather.class);
//      return weather;
//    }
//    private <T> T invoke(URI url, Class<T> responseType){
//      T weather = null;
//      try {
//        RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
//        ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);
//        weather = exchange.getBody();
//      } catch(Exception e){
//      }









    //    -------------------------- BEGIN SEND REQUEST ------------------------//
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

//    return parseResults(places, response);
  }

  private String createURL(double lat, double lng, int radius, String type) {
    log.info(pathApi);
    log.info("type from request (GooglePlaces.class) " + type);

    log.info("should be link to google");
    log.info(pathApi);
    StringBuilder uri = new StringBuilder();

    uri.append(pathApi).append(METHOD_NEARBY_SEARCH);
    uri.append("/json?location=");
    uri.append(lat);
    uri.append(",");
    uri.append(lng);
    uri.append("&radius=");
    uri.append(radius);
    uri.append("&type=");
    uri.append(type);
    uri.append("&key=");
    uri.append(apiKey);
    String url = uri.toString();
    url = url.replace('"', '}');
    url = url.replaceAll("[{}]", "");

    log.info("my url is following (from getNearByPlaces " + url + ". class" + url.getClass());

    return url;
  }

  private List<String> parseResults(List<String> places, String response) {
    JSONObject json = new JSONObject(response);
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
        JSONArray jsonTypes = result.optJSONArray("types");
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
}
