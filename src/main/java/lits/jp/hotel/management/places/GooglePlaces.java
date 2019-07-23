package lits.jp.hotel.management.places;

import lits.jp.hotel.management.exceptions.StaffMemberException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j

public class GooglePlaces {


//    public static final String google = "https://maps.googleapis.com/maps/api/place/";

    // METHODS
    public static final String METHOD_NEARBY_SEARCH = "nearbysearch";

    // ARRAYS
    public static final String ARRAY_RESULTS = "results"; // Array for results

    // DOUBLES
    public static final String DOUBLE_LATITUDE = "lat"; // Latitude of place
    public static final String DOUBLE_LONGITUDE = "lng"; // Longitude of place

    // OBJECTS
    public static final String OBJECT_GEOMETRY = "geometry"; // Geographic information
    public static final String OBJECT_LOCATION = "location"; // Contains latitude and longitude coordinates

    // STRINGS
    public static final String STRING_NAME = "name"; // The name of the place

    private String apiKey = "AIzaSyD-PwiwfuC70H1ZFfqPkX3Wmdm6NCbxxXc"; // ключ працює лише 10 чи 12 запитів в добу. тому зараз поставив "заглушку".


    @Autowired
    private Environment environment;

    public List<String> getNearbyPlaces(List<String> places, double lat, double lng, int radius, String type) throws IOException {
// SAMPLE URL
//    https://maps.googleapis.com/maps/api/place - API_URL
//    /nearbysearch - METHOD_NEARBY_SEARCH
//    /json?
//    location=49.842957, 24.031111
//    &radius=1000
//    &type=bar
//    &key=AIzaSyD-PwiwfuC70H1ZFfqPkX3Wmdm6NCbxxXc

        String pathApi = environment.getProperty("google.url"); // here i get the problem
        System.out.println(pathApi);
        log.info("type from request (GooglePlaces.class) "+type);

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
        String url= uri.toString();
        url = url.replace('"', '}');
        url = url.replaceAll("[{}]", "");

        log.info("my url is following (from getNearByPlaces "+url + ". class"+url.getClass());

        String response = null;

////    -------------------------- BEGIN SEND REQUEST ------------------------//
//        try {
//            final HttpClient client = new DefaultHttpClient();
//            final HttpUriRequest request = new HttpGet(url);
//            final HttpResponse execute = client.execute(request);
//            response = EntityUtils.toString(execute.getEntity());
//        }
//        catch (Exception e)
//        {
//            throw new StaffMemberException("IO Exception here"); // here it gives IO Exception
//        }
////    -------------------------- END SEND REQUEST ------------------------//
//
//
//        // ----------------------- write RESPONSE to file ---------//
//        try{
//            FileWriter fw=new FileWriter("testout.txt");
//            fw.write(response);
//            fw.close();
//        }catch(Exception e){System.out.println(e);}
//        log.info("Response successfully written to file HotelManagementSystem in root (near pom file)...");
//        //------------------ end write RESPONSE to file -----------------//



// ------------------------ BEGIN READ FROM RESPONSEMOCK --------------
        ResponseMock rs = new ResponseMock();
        String contents = rs.giveMockedResponse();
// ------------------------ END READ FROM RESPONSEMOCK --------------


        response = contents;

        JSONObject json = new JSONObject(response);
        JSONArray results = json.getJSONArray(ARRAY_RESULTS);

        for (int i = 0; i < results.length(); i++){

            if(i<=results.length()){

                JSONObject result=results.getJSONObject(i);

                JSONObject location=result.getJSONObject(OBJECT_GEOMETRY).getJSONObject(OBJECT_LOCATION);

                double lat2=location.getDouble(DOUBLE_LATITUDE);
                double lon2=location.getDouble(DOUBLE_LONGITUDE);

                String name=result.optString(STRING_NAME);
                String addr=result.optString("vicinity",null);

                List<String> types = new ArrayList<>();
                JSONArray jsonTypes = result.optJSONArray("types");
                if(jsonTypes!=null){
                    for(int a=0;a<jsonTypes.length();a++){
                        types.add(jsonTypes.getString(a));
                    }
                }
                Place place=new Place(types, lat2, lon2, name, addr);

                places.add(place.toString());
            }
        }
        return places;
    }


}
