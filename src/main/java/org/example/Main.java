package org.example;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.net.URL;

public class Main {
    private static Document getPage() throws IOException {
        String url = "https://pogoda.interia.pl/prognoza-dlugoterminowa-wroclaw,cId,39240";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
//    }
    private static Pattern patern = Pattern.compile("\\d{2}\\.\\d{2}");

    private String getDateFromString(String stringDate){
        Matcher matcher = patern.matcher(stringDate);
        if(matcher.find()){
            return matcher.group();
        }
    }

    public static void main(String[] args) {
        try {
            Document page = getPage();
            Elements weatherInfo = page.select("div.weather-forecast-longterm-list");
            Elements stringDate = page.select("div.weather-forecast-longterm-list-entry-hour");


            if (weatherInfo.isEmpty()) {
                System.out.println("Не вдалося знайти елемент з погодою. Перевірте селектор.");
            } else {
                for (Element element : weatherInfo) {
                    System.out.println("Погода у Вроцлаві: " + element.text());
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка під час отримання сторінки: " + e.getMessage());
        }
    }
}