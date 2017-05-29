package com.placefinder.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(name = "ownerGoogleId", nullable = false)
    private String ownerGoogleId;

    @Column(name = "authorName", nullable = false)
    private String authorName;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name="place_id", nullable=false)
    private Place place;

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerGoogleId() {
        return ownerGoogleId;
    }

    public void setOwnerGoogleId(String ownerGoogleId) {
        this.ownerGoogleId = ownerGoogleId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
