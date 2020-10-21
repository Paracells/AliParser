package ru.paracells;

import ru.paracells.models.MainModel;
import ru.paracells.models.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


// Спарсить 100 товаров по этой ссылке https://flashdeals.aliexpress.com/en.htm? за 1 минуту.
// товары подгружаются динамически при скроле страницы
// offset=12 limit=24
// по вкладке networks браузера, видно, что обращается к странице
// https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery183007873793326438139_1603094442582&widget_id=5547572&platform=pc&limit=12&offset=12&phase=1&productIds2Top=&postback=b632efc1-282d-4b33-8fad-659ab9bf19b8&_=1603094458486

public class Main {


    private static List<String> arrayForConvert = new ArrayList<>(); // сюда будем сохранять элементы для дальнейшего конверта в CSV

    public static void main(String[] args) {

        // шапка для CSV
        arrayForConvert.add(Properties.headerCSV);

        // основной метод
        for (int i = 0; i < Properties.PAGES; i++) {
            final String line = LoadFromWeb.sendRequest(i); // загружаем  файлы из сети

            createArraysWithElements(line); //  конвертируем и сохраняем в список
        }

        saveFile(Properties.FILENAME);

    }

    private static void createArraysWithElements(String onePage) {
        final MainModel mainModel = ConvertToJson.convertToJson(onePage);
        if (mainModel != null) {
            final List<Result> results = mainModel.getResults();
            for (Result result : results) {
                arrayForConvert.add(result.toString());
            }
        }
    }


    // список преобразуем в CSV и пишем в файл
    private static void saveFile(String filename) {
        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            arrayForConvert
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Complete");
    }

}
