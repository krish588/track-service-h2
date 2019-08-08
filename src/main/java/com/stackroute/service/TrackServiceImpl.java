package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
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
            throw new TrackAlreadyExistsException("Track already exists");
        }

        return savedUser;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
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
        if(trackRepository.existsById(trackId))
        {
            trackRepository.deleteById(trackId);
        }
        else
        {
            throw new TrackNotFoundException("track not found");
        }
        return null;
    }

    @Override
    public Track updateComments(int id,Track track) throws GlobalException {
      if(trackRepository.existsById(id))
      {
          track.setTrackComments(track.getTrackComments());
      }
      else
      {
          throw new GlobalException();
      }
      Track track1=trackRepository.save(track);
      return track1;

    }
}
