package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
    TrackService trackService;
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }
@PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
           responseEntity =new ResponseEntity("successfully created",HttpStatus.CREATED);
        }catch (TrackAlreadyExistsException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id)
    {
        try {
            Track track=trackService.getTrackById(id);
            if(track!=null) {
                return new ResponseEntity<String>("track found", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("track not found",HttpStatus.NOT_FOUND);
            }
        }
        catch (TrackNotFoundException e) {
            return new ResponseEntity<String>("track not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("track2/{trackName}")
    public ResponseEntity<?> getByName(@PathVariable("trackName") String trackName)
    {
        try {
            Track track=trackService.getByName(trackName);
            if(track!=null) {
                return new ResponseEntity<String>("track name found", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("track name not found",HttpStatus.NOT_FOUND);
            }
        }
        catch (TrackNotFoundException e) {
            return new ResponseEntity<String>("track name not found",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("track/delete/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable("trackId") int trackId)
    {
        //return new ResponseEntity<String>(trackService.deleteTrack(id),HttpStatus.OK);

        try {
            Track track=trackService.deleteTrack(trackId);
            return new ResponseEntity<String>("track deleted",HttpStatus.OK);
        }
        catch (TrackNotFoundException e) {
            return new ResponseEntity<String>("track name not found",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("track/update/{trackId}")
    public ResponseEntity<?> updateComments(@PathVariable("trackId") int trackId,@RequestBody Track track) throws GlobalException
    {

            Track track1=trackService.updateComments(trackId,track);
            return new ResponseEntity<String>("track updated",HttpStatus.OK);

    }
}
