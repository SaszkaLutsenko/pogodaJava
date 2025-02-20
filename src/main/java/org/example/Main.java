package org.example;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.URL;

public class Main {

    private static Document getPage() throws IOException {
        String url = "https://pogoda.interia.pl/prognoza-dlugoterminowa-wroclaw,cId,39240";
        return Jsoup.parse(new URL(url), 3000);
    }

    private static final Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String extractDate(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        return matcher.find() ? matcher.group() : "Date dont find";
    }

    public static void main(String[] args) {
        try {
            Document page = getPage();
            Elements dateElements = page.select("div.weather-forecast-longterm-list-entry-hour");
            Elements weatherElements = page.select("div.weather-forecast-longterm-list-entry-forecast");

            int dataSize = Math.min(dateElements.size(), weatherElements.size());

            if (dataSize == 0) {
                System.out.println("dont find weather.");
            } else {
                for (int i = 0; i < Math.min(dataSize, 5); i++) {
                    String date = extractDate(dateElements.get(i).text());
                    String weather = weatherElements.get(i).text();
                    System.out.println(date + ": " + weather);
                }
            }
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
