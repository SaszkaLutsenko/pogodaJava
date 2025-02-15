package org.example;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class Main {
    private static Document getPage() throws IOException {
        String url = "https://www.wroclaw.pl/pogoda-wroclaw/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    public static void main(String[] args) {
        try {
            Document page = getPage();

            // Перевіряємо структуру сторінки
            Elements weatherInfo = page.select("div.item");
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