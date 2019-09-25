package com.cloudservice.movierecommender.service.seviceImpl;


import com.cloudservice.movierecommender.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleServiceImpl {

    @Autowired
    private FilmService filmService;

    @Scheduled(fixedRate = 20000)
    public void scheduled(){
        filmService.updateAvgRatings();
        System.out.println("rating updated");
    }
}
