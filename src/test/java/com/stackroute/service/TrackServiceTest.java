package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TrackServiceTest {

    private Track track;

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackId(2);
        track.setTrackName("naadi");
        track.setTrackComments("good  movie album");
        list = new ArrayList<>();
        list.add(track);
    }


    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedUser = trackService.saveTrack(track);
        Assert.assertEquals(track, savedUser);

        //verify here verifies that trackRepository save method is only called once

        verify(trackRepository, times(1)).save(track);

    }


    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws Exception {

        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);
        Assert.assertEquals(track, savedTrack);
    }


    @Test
    public void getAllTracks() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackService.getAllTracks();
        Assert.assertEquals(list, userlist);
    }

    @Test
    public void testUpdateTrackComments() throws GlobalException{

        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        when(trackRepository.save((Track)any())).thenReturn(track);
        track.setTrackComments("good album");
        Track track1=trackService.updateComments(track);
        Assert.assertEquals("good album",track1.getTrackComments());
    }


    @Test(expected = GlobalException.class)
    public void testUpdateTrackCommentsFailure() throws GlobalException{

        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.empty());
        track.setTrackComments("good albums");
        Track track1=trackService.updateComments(track);
    }


    @Test                                                                                           //test for deleteTrack method in music service
    public void deleteTestSuccess() throws TrackNotFoundException {

        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        Track deleteTrack = trackService.deleteTrack(track.getTrackId());
        Assert.assertEquals(null,deleteTrack);

        //verify here verifies that userRepository save method is only called once
        //verify(trackRepository,times(1));

    }
   @Test(expected = TrackNotFoundException.class)
    public void deleteTestFailure() throws TrackNotFoundException {
       when(trackRepository.existsById(track.getTrackId())).thenReturn(false);
       Track deleteTrack = trackService.deleteTrack(track.getTrackId());

    }

    @Test
    public void testTrackByName() throws TrackNotFoundException{
        when(trackRepository.getByName(track.getTrackName())).thenReturn(track);
        Track track1=trackService.getByName(track.getTrackName());
        Assert.assertEquals("naadi",track1.getTrackName());
    }

    @Test(expected = TrackNotFoundException.class)
    public void testTrackByNameFailure() throws TrackNotFoundException{
        when(trackRepository.getByName(track.getTrackName())).thenReturn(null);
        Track track1=trackService.getByName(track.getTrackName());
    }

}

