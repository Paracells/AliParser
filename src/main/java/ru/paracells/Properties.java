package ru.paracells;

public class Properties {

    public static final int OFFSET = 10; // кол-во элементов с одной "страницы"
    public static final int PAGES = 10; // переменная смещения для "пролистки страниц"
    public static final String FILENAME = "result.csv"; // имя файла для сохранения результата конвертирования
    public static String productRegExp = "/\\*\\*/jQuery.*\\w\\("; // для обрезки начала полученной строки

    public static String headerCSV =
            "productId,"+
            "sellerId," +
            "oriMinPrice," +
            "oriMaxPrice," +
            "promotionId," +
            "startTime," +
            "endTime," +
            "phase," +
            "productTitle," +
            "minPrice," +
            "maxPrice," +
            "discount," +
            "totalStock," +
            "stock," +
            "orders," +
            "soldout," +
            "productImage," +
            "productDetailUrl," +
            "trace," +
            "totalTranpro3," +
            "productPositiveRate," +
            "productAverageStar," +
            "itemEvalTotalNum," +
            "gmtCreate,";

}
