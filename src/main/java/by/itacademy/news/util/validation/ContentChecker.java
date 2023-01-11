package by.itacademy.news.util.validation;

import java.util.Arrays;

public class ContentChecker {

    private static final ContentChecker instance = new ContentChecker();

    private ContentChecker() {
    }

    public static ContentChecker getInstance() {
        return instance;
    }

    public boolean isEmpty(String... content) {
        return Arrays.stream(content).anyMatch(String::isEmpty);
    }
}
