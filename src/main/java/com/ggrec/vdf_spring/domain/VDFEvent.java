package com.ggrec.vdf_spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "vdf_event")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VDFEvent implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private LocalDate dateStart;
    private LocalDate dateEnd;

    @Column(length = 1000)
    private String description;

    private String organizer;

    private String schedule;

    private String prizes;

    private String ageCategories;

    private String tracks;

    private String locationName;
    private String locationCoordinates;

    private String photoLink;

    private String registrationTax;

    private String registrationLink;

    private String technicalGuideLink;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<VDFEventTag> tags;

    public Set<VDFEventTag> getTags() {
        return tags;
    }

    public void setTags(Set<VDFEventTag> tags) {
        this.tags = tags;
    }

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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getRegistrationTax() {
        return registrationTax;
    }

    public void setRegistrationTax(String registrationTax) {
        this.registrationTax = registrationTax;
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

    public String getTracks() {
        return tracks;
    }

    public void setTracks(String tracks) {
        this.tracks = tracks;
    }

    public String getAgeCategories() {
        return ageCategories;
    }

    public void setAgeCategories(String ageCategories) {
        this.ageCategories = ageCategories;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

}
