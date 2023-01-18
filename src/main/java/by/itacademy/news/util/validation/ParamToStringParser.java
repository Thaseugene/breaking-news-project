package by.itacademy.news.util.validation;

import java.util.Map;

public class ParamToStringParser {

    private static final ParamToStringParser instance = new ParamToStringParser();

    private ParamToStringParser() {
    }

    public static ParamToStringParser getInstance() {
        return instance;
    }

    public String convertToStringPath(Map<?, ?> params) {
        StringBuilder stringPath = new StringBuilder();
        params.forEach((key, value) -> stringPath.append(key).append("=").append(value).append("&"));
        return stringPath.deleteCharAt(stringPath.length()-1).toString();
    }

    public String convertToStringPath(String paramName, String param) {
        return String.format("%s=%s",paramName, param);
    }

}
