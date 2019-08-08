package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl1 implements TrackService {
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public List<Track> getAllTracks() {
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track getByName(String trackName) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track deleteTrack(int trackId) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track updateComments(int id, Track track) throws GlobalException {
        return null;
    }

}
