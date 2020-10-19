package ru.paracells;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertToCSV {


    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(ConvertToCSV::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public static String escapeSpecialCharacters(String data) {
        if (data == null)
            data = " ";
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "");
            data = data.replace(",",".");
            escapedData = data;
        }
        return escapedData;
    }
}
