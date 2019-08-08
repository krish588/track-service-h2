package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();

    public Track getTrackById(int id) throws TrackNotFoundException;

    public Track getByName(String trackName) throws TrackNotFoundException;

    public Track deleteTrack(int trackId) throws TrackNotFoundException;

    public Track updateComments(int id,Track track) throws GlobalException;
}
