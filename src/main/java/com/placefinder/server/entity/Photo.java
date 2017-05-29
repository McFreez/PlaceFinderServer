package com.placefinder.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "photo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Photo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "ownerGoogleId", nullable = false)
    private String ownerGoogleId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="place_id", nullable=false)
    private Place place;

    public Photo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOwnerGoogleId() {
        return ownerGoogleId;
    }

    public void setOwnerGoogleId(String ownerGoogleId) {
        this.ownerGoogleId = ownerGoogleId;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
