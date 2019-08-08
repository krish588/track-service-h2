package com.stackroute.repository;

import com.stackroute.domain.Track;

import com.stackroute.exception.TrackNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

    @Query("select t from Track t where t.trackName=:trackName")
    public Track getByName(@Param("trackName") String trackName) throws TrackNotFoundException;

}
