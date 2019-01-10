package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Adel Chakouri
 */
@Entity
public class Race extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private Date date;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private Set<RaceParticipation> raceParticipations = new HashSet<>();

    public Race(@NotNull Date date, @NotBlank String title, @NotBlank String location) {
        this.date = date;
        this.title = title;
        this.location = location;
    }

    protected Race() {
    }

    public Set<RaceParticipation> getRaceParticipations() {
        return raceParticipations;
    }

    public void setRaceParticipations(Set<RaceParticipation> raceParticipations) {
        this.raceParticipations = raceParticipations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    @NotBlank
    public String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Race)) return false;

        Race race = (Race) o;

        if (date != null ? !date.equals(race.date) : race.date != null) return false;
        return title != null ? title.equals(race.title) : race.title == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Race{" +
                "date='" + getDate() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", location='" + getLocation() + '\'' +
                "} " + super.toString();
    }
}
