package by.itacademy.news.util.validation;

import java.util.Arrays;
import java.util.Objects;

public class ContentChecker {

    private static final ContentChecker instance = new ContentChecker();

    private ContentChecker() {
    }

    public static ContentChecker getInstance() {
        return instance;
    }

    public boolean isEmpty(String... content) {
        if (!isNull(content)) {
            return Arrays.stream(content).anyMatch(String::isEmpty);
        } else {
            return true;
        }
    }

    public boolean isNull (String... content) {
        return Arrays.stream(content).anyMatch(Objects::nonNull);
    }
}
