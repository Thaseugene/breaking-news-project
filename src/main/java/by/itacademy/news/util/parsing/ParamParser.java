package by.itacademy.news.util.parsing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class ParamParser {

    private static final ParamParser instance = new ParamParser();
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm a", Locale.US);

    private ParamParser() {
    }

    public static ParamParser getInstance() {
        return instance;
    }

    public String convertToStringPath(Map<?, ?> params) throws ParsingParamException {
        if (params.isEmpty()) {
            throw new ParsingParamException("Unknown problems");
        } else {
            StringBuilder stringPath = new StringBuilder();
            params.forEach((key, value) -> stringPath.append(key).append("=").append(value).append("&"));
            return stringPath.deleteCharAt(stringPath.length()-1).toString();
        }
    }

    public String convertToStringPath(String paramName, String param) {
        return String.format("%s=%s",paramName, param);
    }

    public Date parseDate(String date, String time) throws ParseException {
        return formatter.parse(String.format("%s %s", date, time));
    }

}
