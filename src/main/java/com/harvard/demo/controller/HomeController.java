package com.harvard.demo.controller;

import com.harvard.demo.entity.LocationStats;
import com.harvard.demo.service.CoronavirusDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    private final CoronavirusDataService coronavirusDataService;

    public HomeController(CoronavirusDataService coronavirusDataService) {
        this.coronavirusDataService = coronavirusDataService;
    }

    @GetMapping("/")
    public String getIndex(Model model) throws IOException, InterruptedException {
        List<LocationStats> allStats = coronavirusDataService.fetchData();
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestDayCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();
        model.addAttribute("locationStatsList", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "index";
    }
}
