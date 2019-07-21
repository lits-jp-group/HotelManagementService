package lits.jp.hotel.management.places;

// experinemt by loco
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class GooglePlaces {

    public static final String API_URL = "https://maps.googleapis.com/maps/api/place/";

    // METHODS
    public static final String METHOD_NEARBY_SEARCH = "nearbysearch";

    // ARRAYS
    public static final String ARRAY_RESULTS = "results"; // Array for results
    public static final String ARRAY_TYPES = "types"; // Types of place

    // DOUBLES
    public static final String DOUBLE_LATITUDE = "lat"; // Latitude of place
    public static final String DOUBLE_LONGITUDE = "lng"; // Longitude of place
    public static final String DOUBLE_RATING = "rating"; // The "rating" of the place

    // OBJECTS
    public static final String OBJECT_GEOMETRY = "geometry"; // Geographic information
    public static final String OBJECT_LOCATION = "location"; // Contains latitude and longitude coordinates


    // STRINGS
    public static final String STRING_ID = "id"; // The unique, stable, identifier for this place
    public static final String STRING_NAME = "name"; // The name of the place

    //TYPES
    public static final String TYPE_BANK = "bank";
    public static final String TYPE_BAR = "bar";
    public static final String TYPE_BEAUTY_SALON = "beauty_salon";
    public static final String TYPE_CAFE = "cafe";
    public static final String TYPE_CHURCH = "church";
    public static final String TYPE_FOOD = "food";
    public static final String TYPE_UNIVERSITY = "university";
    public static final String TYPE_ZOO = "zoo";

    private String apiKey = "AIzaSyD-PwiwfuC70H1ZFfqPkX3Wmdm6NCbxxXc"; // ключ працює лише 10 чи 12 запитів в добу. тому зараз поставив "заглушку".

    public List<String> getNearbyPlaces(List<String> places, double lat, double lng, int radius, String type) throws IOException {
// SAMPLE URL
//    https://maps.googleapis.com/maps/api/place - API_URL
//    /nearbysearch - METHOD_NEARBY_SEARCH
//    /json?
//    location=49.842957, 24.031111
//    &radius=1000
//    &type=bar
//    &key=AIzaSyD-PwiwfuC70H1ZFfqPkX3Wmdm6NCbxxXc
        String uri = String.format("%s%s/json?location=%f;%f&radius=%d&type=%s&key=%s",
                API_URL,
                METHOD_NEARBY_SEARCH,
                lat,
                lng,
                radius,
                type,
                apiKey);
        uri = uri.replace(',', '.');
        uri = uri.replace(';', ',');

        System.out.println("my url is following (from getNearByPlaces "+uri + ". class"+uri.getClass());

        String response = null;

////    -------------------------- BEGIN SEND REQUEST ------------------------//
//        try {
//            final HttpClient client = new DefaultHttpClient();
//            final HttpUriRequest request = new HttpGet(uri);
//            final HttpResponse execute = client.execute(request);
//            response = EntityUtils.toString(execute.getEntity());
//        }
//        catch (Exception e)
//        {
//            throw new MyLococException("IO Exception here"); // here it gives IO Exception
//        }
////    -------------------------- END SEND REQUEST ------------------------//


//        // ----------------------- write RESPONSE to file ---------//
//        try{
//            FileWriter fw=new FileWriter("D:\\testout.txt");
//            fw.write(response);
//            fw.close();
//        }catch(Exception e){System.out.println(e);}
//        System.out.println("Success...");
//        //------------------ end write RESPONSE to file -----------------//


//
// ------------------------ BEGIN READ FROM RESPOSEMOCK --------------
        ResponseMock rs = new ResponseMock();
        String contents = rs.giveResponse2();

        response = contents;

        JSONObject json = new JSONObject(response);
        JSONArray results = json.getJSONArray(ARRAY_RESULTS);

        for (int i = 0; i < results.length(); i++){

            if(i<=results.length()){

                JSONObject result=results.getJSONObject(i);

                // location
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
        System.out.println(places);
        return places;
    }
}
