package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Downloader {
    /**
     * Downloads specified resource
     * @param url Uniform resource locator
     * @return Dowloaded text
     * @throws IOException On failed download.
     */
    public static String fetchURL(String url) throws IOException {
        URL address = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(address.openStream())
        );
        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) {
            result +=inputLine;
        }
        in.close();
        return result;
    }
}
