package com.xbaychallenge.statistics.provider;

public interface CategoryStatisticsProvider<T> {

    String getName();

    T getValue(String category);
}
