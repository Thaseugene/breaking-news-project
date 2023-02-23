package by.itacademy.news.service;

import by.itacademy.news.model.News;

import java.util.Comparator;

public enum NewsCompareType {

    BY_DATE(Comparator.comparing(News::getPublicationDate).reversed());

    private final Comparator<News> comparator;


    NewsCompareType(Comparator<News> comparator) {
        this.comparator = comparator;
    }

    public Comparator<News> getComparator() {
        return comparator;
    }

}
