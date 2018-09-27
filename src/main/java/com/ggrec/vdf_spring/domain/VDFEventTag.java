package com.ggrec.vdf_spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "vdf_event_tag")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VDFEventTag {

    private static final String DEFAULT_CATEGORY = "Miscellaneous";

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 20)
    private String name;

    @Column(columnDefinition="VARCHAR(20) default '" + DEFAULT_CATEGORY + "'")
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
