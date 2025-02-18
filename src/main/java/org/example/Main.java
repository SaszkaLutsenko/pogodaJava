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

    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        return "dont found data";
    }

    private void printForValue(Elements weatherElements, int index){
        for(int i = 0; i < 4; i++){
            Element weater = weatherElements.get(index);

        }

    }

    public static void main(String[] args) {
        try {
            int index;
            Document page = getPage();
            Elements dateElements = page.select("div.weather-forecast-longterm-list-entry-hour");
            Elements weatherElements = page.select("div.weather-forecast-longterm-list-entry-forecast");

            if (dateElements.isEmpty() || weatherElements.isEmpty()) {
                System.out.println("try again.");
            } else {
                for (int i = 0; i < 5; i++) {
                    String date = getDateFromString(dateElements.get(i).text());
                    String weather = weatherElements.get(i).text();
                    System.out.println(date + ": " + weather);
                }
            }
        } catch (IOException e) {
            System.out.println("eror " + e.getMessage());
        }
    }
}