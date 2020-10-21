package ru.paracells;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class LoadFromWeb {

    // https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18306968468728800121_1603095262887&widget_id=5547572&platform=pc&limit=12&offset=24&phase=1&productIds2Top=&postback=f456d1dd-87f7-4c5f-953a-eb46e9171152&_=1603113838198
    public static String sendRequest(int offset) {
        HttpGet request = new HttpGet("https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=jQuery18306968468728800121_1603095262887&widget_id=5547572&platform=pc&limit=10&offset=" + offset * Properties.OFFSET + "&phase=1&productIds2Top=&postback=b632efc1-282d-4b33-8fad-659ab9bf19b8&_=1603094458486");
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request)
        ) {
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new IOException("Код ответа не 200, ошибка загрузки в странице");
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("Downloading and Parsing " + (offset * Properties.OFFSET+Properties.OFFSET) + " from 100" + " files");
                return EntityUtils.toString(entity);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка в странице");
        return " ";
    }
}
