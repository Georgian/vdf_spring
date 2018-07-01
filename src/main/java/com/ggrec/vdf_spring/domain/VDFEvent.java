package com.ggrec.vdf_spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity(name = "vdf_event")
public class VDFEvent {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String sport;
    private String discipline;
    // private List<String> levels;
    // private List<String> ageCategories;
    // private List<String> distanceCategories
    @OneToOne
    private VDFOrganizer organizer;
    private LocalDate date;
    private String timeSchedule;
    private String price;
    private String locationName;
    private String locationCoordinates;
    private String registrationLink;
    private String technicalGuideLink;
    private String photoLink;
    private String trackLinks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public VDFOrganizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(VDFOrganizer organizer) {
        this.organizer = organizer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(String timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    public String getRegistrationLink() {
        return registrationLink;
    }

    public void setRegistrationLink(String registrationLink) {
        this.registrationLink = registrationLink;
    }

    public String getTechnicalGuideLink() {
        return technicalGuideLink;
    }

    public void setTechnicalGuideLink(String technicalGuideLink) {
        this.technicalGuideLink = technicalGuideLink;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getTrackLinks() {
        return trackLinks;
    }

    public void setTrackLinks(String trackLinks) {
        this.trackLinks = trackLinks;
    }
}
