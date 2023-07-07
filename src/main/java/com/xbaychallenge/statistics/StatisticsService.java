package com.xbaychallenge.statistics;

import com.xbaychallenge.statistics.provider.CategoryStatisticsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final List<CategoryStatisticsProvider<?>> providers;

    public Map<String, String> getStatistics(String category) {
        return providers.stream()
                .collect(Collectors.toMap(
                        CategoryStatisticsProvider::getName,
                        p -> p.getValue(category).toString()));
    }
}
