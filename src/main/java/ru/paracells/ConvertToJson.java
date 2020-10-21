package ru.paracells;

import com.google.gson.Gson;
import ru.paracells.models.MainModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertToJson {
    private static Pattern pattern = Pattern.compile(Properties.productRegExp); // для обрезки начала строки
    private static StringBuilder sb;
    private static final Gson gson = new Gson();


    public static MainModel convertToJson(String onePage) {
        sb = new StringBuilder(onePage);
        sb.setLength(sb.length() - 2); // убираем ); с конца строки, чтобы не было ошибки конвертирования
        final Matcher matcher = pattern.matcher(onePage);
        if (!matcher.find()) {
            System.out.println("Проверьте правильность данных");
            return null;
        } else {
            sb.replace(matcher.start(), matcher.end(), "");
            return gson.fromJson(sb.toString(), MainModel.class);
        }
    }
}
