package utils;

import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Downloader {
    static Map<URL, String> cache = null;
    static Map<URL, DateTime> downloadInProgress = null;
    /**
     * Downloads specified resource
     * @param url Uniform resource locator
     * @return Dowloaded text
     * @throws IOException On failed download.
     */
    public static String fetchURL(String url) throws IOException {
        URL address = new URL(url);
        if(cache == null){
            cache = new ConcurrentHashMap<>();
        }
        if(downloadInProgress == null){
            downloadInProgress = new ConcurrentHashMap<>();
        }
        if(cache.containsKey(address)){
            return cache.get(address);
        }
        if(downloadInProgress.containsKey(address)){
            while (true){
                if(downloadInProgress.get(address).isBefore(DateTime.now().minusMinutes(1))){
                    downloadInProgress.remove(address);
                    break;
                }
                if(cache.containsKey(address)){
                    return cache.get(address);
                }
                try {
                    Thread.sleep(100);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        downloadInProgress.put(address, DateTime.now());
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
