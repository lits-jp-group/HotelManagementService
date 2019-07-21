package lits.jp.hotel.management.places;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResponseMock {
    public String giveResponse (String response){

        String contents = null;
        try {
            contents = new String(Files.readAllBytes(Paths.get("d://response.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response " + contents);
        response = contents;
        return response;
    }

    public String giveResponse2 () throws IOException {
        FileInputStream fis =  new FileInputStream("d://response.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(fis, "Cp1251"));
        String myString = r.readLine();
        r.close();
        return myString;
    }
}
