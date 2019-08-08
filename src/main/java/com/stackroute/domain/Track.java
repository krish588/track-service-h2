package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="track")
public class Track {
    @Column(name="trackid")
    @Id
    int trackId;
    @Column(name="trackname")
    String trackName;
    @Column(name="trackcomments")
    String trackComments;

}
