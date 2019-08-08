package com.stackroute.repository;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setup()
    {
        track=new Track();
        track.setTrackId(1);
        track.setTrackName("dear comrade");
        track.setTrackComments("rashmika");
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrackSuccess()
    {
        trackRepository.save(track);
        Track fetchTrack=trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1,fetchTrack.getTrackId());
    }


    @Test
    public void testSaveTrackFailure(){
        Track testUser = new Track(2,"dear comrade","rashmika");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testUser,track);
    }

    @Test
    public void testGetAllTracks() {
        Track t = new Track(3,"dear comrade","rasmika");
        Track t1 = new Track(4,"mega ","all tracks");
        trackRepository.save(t);
        trackRepository.save(t1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals(3, list.get(0).getTrackId());
    }

    @Test
    public void testDeleteTrackSuccess(){
        //Track track=new Track(2,"chilaka","parr");
        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(1);
        Assert.assertEquals(false,deletedTrack);
    }

    @Test
    public void testDeleteTrackFailure(){
        //Track track=new Track(2,"chilaka","parr");
        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(1);
        Assert.assertNotSame(true,deletedTrack);
    }

    @Test
    public void testUpdateTrackSuccess() {
    trackRepository.save(track);
    trackRepository.findById(track.getTrackId()).get().setTrackComments("dear comrade");
    List<Track> list=trackRepository.findAll();
    Assert.assertEquals("dear comrade",list.get(0).getTrackComments());
}

    @Test                                                                                                  //test for failure of update method
    public void testUpdateTrackFailure() {
       //Track testUser = new Track(1, "premam", "good");
        trackRepository.save(track);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("dear comrade");
        List<Track> list=trackRepository.findAll();
        Assert.assertNotSame(" ",list.get(0).getTrackComments());
    }

    @Test
    public void testgetTrackByNameSuccess() {
        trackRepository.save(track);
//        Track trackList=trackRepository.getByName("premam");
        Assert.assertEquals(track,trackRepository.getByName("premam"));
    }
    @Test
    public void testgetTrackByNameFailure() {
        trackRepository.save(track);
//        Track trackList=trackRepository.getByName("premam");
        Assert.assertNotSame(track,trackRepository.getByName("premam"));
    }
}
