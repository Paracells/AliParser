package ru.paracells;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Спарсить 100 товаров по этой ссылке https://flashdeals.aliexpress.com/en.htm? за 1 минуту.
// товары подгружаются динамически при скроле страницы
// offset=12 limit=24
// по вкладке networks браузера, видно, что обращается к странице
// https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery183007873793326438139_1603094442582&widget_id=5547572&platform=pc&limit=12&offset=12&phase=1&productIds2Top=&postback=b632efc1-282d-4b33-8fad-659ab9bf19b8&_=1603094458486
// ответ, который получает похож на json

public class Main {


    public static final int NUMBER_OF_PROPERTIES = 23; // кол-во свойств, которое будем хранить в массиве для будущего сохранения в list
    private static final int OFFSET = 10; // кол-во элементов с одной "страницы"
    public static final int PAGES = 11; // переменная смещения для "пролистки страниц"
    public static final String FILENAME = "result.csv"; // имя файла для сохранения результата конвертирования
    // далее будем пагинировать запрос для передвижения по другим страницам
    // забавно: параметр limit можно поставить любой, НО, максимум будет 25 (даже, если мы поставим 30, 50, 100, однако, в любом случае, почему-то ответ
    // на запрос в некоторых полях бывает пустой


    private static String productRegExp =
            "(\"productId\":\\d*)|" +
                    "(\"sellerId\":\\d*)|" +
                    "(\"oriMinPrice\":\"([^\"]*)\")|" +
                    "(\"oriMaxPrice\":\"([^\"]*)\")|" +
                    "(\"promotionId\":\\d*)|" +
                    "(\"startTime\":\\d*)|" +
                    "(\"endTime\":\\d*)|" +
                    "(\"phase\":\\d*)|" +
                    "(\"productTitle\":\"([^\"]*)\")|" +
                    "(\"minPrice\":\"([^\"]*)\")|" +
                    "(\"maxPrice\":\"([^\"]*)\")|" +
                    "(\"discount\":\"([^\"]*)\")|" +
                    "(\"totalStock\":\"([^\"]*)\")|" +
                    "(\"stock\":\"([^\"]*)\")|" +
                    "(\"orders\":\"([^\"]*)\")|" +
                    "(\"soldout\":false|\"soldout\":true)|" +
                    "(\"productImage\":\"([^\"]*)\")|" +
                    "(\"productDetailUrl\":\"([^\"]*)\")|" +
                    "(\"totalTranpro3\":\"([^\"]*)\")|" +
                    "(\"productPositiveRate\":\"([^\"]*)\")|" +
                    "(\"productAverageStar\":\"([^\"]*)\")|" +
                    "(\"itemEvalTotalNum\":\\d*)|" +
                    "(\"gmtCreate\":\\d*)";

    private static Pattern pattern = Pattern.compile(productRegExp);

    private static List<String[]> modelList = new ArrayList<>(); // сюда будем сохранять элементы для дальнейшего конверта в CSV


    public static void main(String[] args) throws IOException, InterruptedException {

        // шапка для CSV
        final String[] headerOfCSV = createHeaderOfCSV();
        modelList.add(headerOfCSV);

        // основной метод
        for (int i = 0; i <= PAGES; i++) {
            final String line = sendRequest(i); // загружаем  файлы из сети
            createArraysWithElements(line); //  конвертируем и сохраняем в список
        }

        saveFile(FILENAME);

    }

    private static void createArraysWithElements(String body) {
        int sizeOfModel = 0;
        String[] line = new String[NUMBER_OF_PROPERTIES];
        Matcher matcher = pattern.matcher(body);
        while (matcher.find()) {
            final String rawString = body.substring(matcher.start(), matcher.end()); // получаем "грязную" строку
            String[] element = rawString.split(":"); // разбиваем, чтобы сохранить значение  свойства
            if (sizeOfModel > NUMBER_OF_PROPERTIES - 1) { // может уже перебрали все свойства и пора сохранить?
                modelList.add(line);
                line = new String[NUMBER_OF_PROPERTIES];
                sizeOfModel = 0;
            }
            line[sizeOfModel] = element[1];
            sizeOfModel++;
        }
    }

    // https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18306968468728800121_1603095262887&widget_id=5547572&platform=pc&limit=12&offset=24&phase=1&productIds2Top=&postback=f456d1dd-87f7-4c5f-953a-eb46e9171152&_=1603113838198
    private static String sendRequest(int offset) {
        HttpGet request = new HttpGet("https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18306968468728800121_1603095262887&widget_id=5547572&platform=pc&limit=10&offset=" + offset * OFFSET + "&phase=1&productIds2Top=&postback=b632efc1-282d-4b33-8fad-659ab9bf19b8&_=1603094458486");
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request);
        ) {
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new IOException("Код ответа не 200, ошибка загрузки в странице");
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("Downloading and Parsing " + (offset * OFFSET) + " from 100" + " files");
                return EntityUtils.toString(entity);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка в странице");
        return " ";
    }

    // список преобразуем в CSV и пишем в файл
    private static void saveFile(String filename) {
        File csvOutputFile = new File(FILENAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            modelList.stream()
                    .map(ConvertToCSV::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Complete");
    }

    private static String[] createHeaderOfCSV() {
        return new String[]{
                "productId",
                "sellerId",
                "oriMinPrice",
                "oriMaxPrice",
                "promotionId",
                "startTime",
                "endTime",
                "phase",
                "productTitle",
                "minPrice",
                "maxPrice",
                "discount",
                "totalStock",
                "stock",
                "orders",
                "soldout",
                "productImage",
                "productDetailUrl",
                "totalTranpro3",
                "productPositiveRate",
                "productAverageStar",
                "itemEvalTotalNum",
                "gmtCreate",
        };
    }

}
