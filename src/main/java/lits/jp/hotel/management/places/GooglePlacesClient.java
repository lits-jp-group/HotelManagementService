package lits.jp.hotel.management.places;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This example demonstrates the use of {@link HttpPost} request method. And
 * sending HTML Form request parameters
 */
public class GooglePlacesClient {

        public static void main(String...args) throws IOException {

            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

                List < NameValuePair > form = new ArrayList < > ();
                form.add(new BasicNameValuePair("John", "Cena"));
                form.add(new BasicNameValuePair("Tom", "Cruise"));
                form.add(new BasicNameValuePair("tony", "stark"));
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);

                HttpPost httpPost = new HttpPost("http://httpbin.org/post");
                httpPost.setEntity(entity);
                System.out.println("Executing request " + httpPost.getRequestLine());

                // Create a custom response handler
                ResponseHandler < String > responseHandler = response - > {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity responseEntity = response.getEntity();
                    return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
                String responseBody = httpclient.execute(httpPost, responseHandler);
                System.out.println("----------------------------------------");
                System.out.println(responseBody);
            }
        }
    }
//    private static final String GOOGLE_API_KEY  = "***";
//
//    CloseableHttpClient httpclient = HttpClients.createDefault();
//    HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");
//
//    // Request parameters and other properties.
//    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//        params.add(new BasicNameValuePair("param-1", "12345"));
//        params.add(new BasicNameValuePair("param-2", "Hello!"));
//        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//
//    //Execute and get the response.
//    HttpResponse response = httpclient.execute(httppost);
//    HttpEntity entity = response.getEntity();
//
//        if (entity != null) {
//    try (InputStream instream = entity.getContent()) {
//        // do something useful
//    }
//}
//}

//
//        private final HttpClient client = new DefaultHttpClient();
//
//    public static void main(final String[] args) throws ParseException, IOException, URISyntaxException
//    {
//        new GooglePlacesClient().performSearch("establishment", 8.6668310, 50.1093060);
//    }
//
//    public void performSearch(final String types, final double lon, final double lat) throws ParseException, IOException, URISyntaxException
//    {
//        final URLBuilder builder = new URLBuilder().setScheme("https").setHost("maps.googleapis.com").setPath("/maps/api/place/search/json");
//
//        builder.addParameter("location", lat + "," + lon);
//        builder.addParameter("radius", "5");
//        builder.addParameter("types", types);
//        builder.addParameter("sensor", "true");
//        builder.addParameter("key", GooglePlacesClient.GOOGLE_API_KEY);
//
//        final HttpUriRequest request = new HttpGet(builder.build());
//
//        final HttpResponse execute = this.client.execute(request);
//
//        final String response = EntityUtils.toString(execute.getEntity());
//
//        System.out.println(response);
//    }

//}

