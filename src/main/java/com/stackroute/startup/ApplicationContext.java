package com.stackroute.startup;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationContext implements ApplicationListener<ContextRefreshedEvent> {
    TrackRepository trackRepository;

    @Autowired
    private Environment environment;

/*    @Value("${spring.Track.trackId1}")
    private int trackId;
    @Value("${spring.Track.trackName1}")
    private String trackName;
    @Value("${spring.Track.trackComments1}")
    private String trackComments;*/



    public ApplicationContext(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Track track=new Track(Integer.parseInt(environment.getProperty("spring.Track.trackId1")),environment.getProperty("spring.Track.trackName1"),
                environment.getProperty("spring.Track.trackComments1"));
        trackRepository.save(track);

    }
}
