package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


//Remove hardcoded values


@Service
@Primary
@CacheConfig(cacheNames = "track")
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
    }

    public void simulateDelay()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException{
        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track savedUser=trackRepository.save(track);
        if(savedUser==null)
        {
            throw new TrackAlreadyExistsException("Track not null");
        }

        return savedUser;
    }

    @Cacheable
    @Override
    public List<Track> getAllTracks() {

        simulateDelay();
        List<Track> list=(List<Track>)trackRepository.findAll();
        return list;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException{
        Track track=null;
        if(trackRepository.existsById(id))
        {
            track=trackRepository.getOne(id);
        }
        else
        {
            throw new TrackNotFoundException("track not exists");
        }
        if(track==null){
            throw new TrackNotFoundException("track not found");
        }
        return track;
    }

    @Override
    public Track getByName(String trackName) throws TrackNotFoundException {
        Track track=null;
        track=trackRepository.getByName(trackName);
        if(track==null){
            throw new TrackNotFoundException("track name not found");
        }
        return track;
    }

    @Override
    public Track deleteTrack(int trackId) throws TrackNotFoundException {
        //Track track=trackRepository.findById(trackId).get();
        if(trackRepository.existsById(trackId))
        {
            trackRepository.deleteById(trackId);
        }
        else
        {
            throw new TrackNotFoundException("track not found");
        }
        return null;   //return track object
    }

    @Override
//    @CachePut("track")
    @CacheEvict(allEntries = true)
    public Track updateComments(Track track) throws GlobalException {
        Track track1=null;
      if(trackRepository.existsById(track.getTrackId()))
      {
          track.setTrackComments(track.getTrackComments()); //trackRepository.save(Track)
           track1=trackRepository.save(track);
      }
      else
      {
          throw new GlobalException();
      }

      return track1;

    }
}
