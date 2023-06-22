package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.TopBookDTO;
import cn.rjys365.sebookstorebackend.dto.UserBookStatsDTO;
import cn.rjys365.sebookstorebackend.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/statistics/")
@CrossOrigin("http://localhost:3000")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/top_books")
    public List<TopBookDTO> findTopBooksByTotalCountBetweenDates(
            @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate,
            @RequestParam(required = false) Integer userId) {
        if (userId != null)
            return statisticsService.findTopBooksByUserIdAndTotalCountBetweenDates(startDate, endDate, userId);
        return statisticsService.findTopBooksByTotalCountBetweenDates(startDate, endDate);
    }

    @GetMapping("/user_stats/")
    public List<UserBookStatsDTO> findAllUsersBookStatsByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return statisticsService.findAllUsersBookStatsByDateRange(startDate, endDate);
    }

    @GetMapping("/user_stats/{userId}")
    public UserBookStatsDTO findUserBookStatsByUserIdAndDateRange(LocalDateTime startDate, LocalDateTime endDate, @PathVariable Integer userId) {
        return statisticsService.findUserBookStatsByUserIdAndDateRange(startDate, endDate, userId);
    }
}
