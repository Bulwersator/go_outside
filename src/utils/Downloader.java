package utils;

import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Downloader {
    private static Map<String, String> cache = null;
    private static Map<String, DateTime> downloadInProgress = null;

    private Downloader() {
    }

    private static void avoidDuplicateDownloads(String url) {
        if (downloadInProgress.containsKey(url)) {
            while (downloadInProgress.get(url).isAfter(DateTime.now().minusMinutes(1))) {
                if (cache.containsKey(url)) {
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
            downloadInProgress.remove(url);
        }
    }

    /**
     * Downloads specified resource
     *
     * @param url Uniform resource locator
     * @return Dowloaded text
     * @throws IOException On failed download.
     */
    public static String fetchURL(String url) throws IOException {
        if (cache == null || downloadInProgress == null) {
            synchronized (Downloader.class){
                if (cache == null) {
                    cache = new ConcurrentHashMap<>();
                }
                if (downloadInProgress == null) {
                    downloadInProgress = new ConcurrentHashMap<>();
                }
            }
        }
        avoidDuplicateDownloads(url);
        if (cache.containsKey(url)) {
            return cache.get(url);
        }
        downloadInProgress.put(url, DateTime.now());
        System.out.println(url);
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new URL(url).openStream())
        )){
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
        }
        cache.put(url, result.toString());
        return result.toString();
    }
}
