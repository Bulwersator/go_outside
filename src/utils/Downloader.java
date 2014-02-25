package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Downloader {
    static Map<URL, String> cache = null;
    /**
     * Downloads specified resource
     * @param url Uniform resource locator
     * @return Dowloaded text
     * @throws IOException On failed download.
     */
    public static String fetchURL(String url) throws IOException {
        URL address = new URL(url);
        if(cache == null){
            cache = new HashMap<>();
        }
        if(cache.containsKey(address)){
            return cache.get(address);
        }
        System.out.println(address);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(address.openStream())
        );
        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) {
            result +=inputLine;
        }
        in.close();
        cache.put(address, result);
        return result;
    }
}
