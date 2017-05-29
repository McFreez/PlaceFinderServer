package com.placefinder.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "place")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Place {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "ownerGoogleId", nullable = false)
    private String ownerGoogleId;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @OneToMany(mappedBy="place")
    private Set<Photo> photoSet;

    @OneToMany(mappedBy="place")
    private Set<Comment> commentSet;

    public Place() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerGoogleId() {
        return ownerGoogleId;
    }

    public void setOwnerGoogleId(String ownerGoogleId) {
        this.ownerGoogleId = ownerGoogleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
