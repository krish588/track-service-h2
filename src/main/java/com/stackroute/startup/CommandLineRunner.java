package com.stackroute.startup;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner{

    TrackRepository trackRepository;

    @Autowired
    private Environment environment;

   /* @Value("${spring.Track.trackId2}")
    private int trackId;
    @Value("${spring.Track.trackName2}")
    private String trackName;
    @Value("${spring.Track.trackComments2}")
    private String trackComments;*/

    public CommandLineRunner(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Track track=new Track(Integer.parseInt(environment.getProperty("spring.Track.trackId2")),environment.getProperty("spring.Track.trackName2"),
                environment.getProperty("spring.Track.trackComments2"));
        trackRepository.save(track);

    }
}
