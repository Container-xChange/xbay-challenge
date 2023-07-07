package com.xbaychallenge.statistics.web;

import com.xbaychallenge.statistics.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public Map<String, String> getListing(@RequestParam String category) {
        return statisticsService.getStatistics(category);
    }
}
