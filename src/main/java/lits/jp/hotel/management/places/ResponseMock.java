package lits.jp.hotel.management.places;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResponseMock {

  public String giveMockedResponse() throws IOException {
    FileInputStream fis =
        new FileInputStream(
            "/home/loco/IdeaProjects/HotelManagementService/src/main/java/lits/jp/hotel/management/places/response.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(fis, "Cp1251"));
    String myString = r.readLine();
    r.close();
    return myString;
  }
}
