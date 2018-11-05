package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Adel Chakouri
 */
@Entity
public class Race extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private Date date;

    @NotNull
    @Column(nullable = false, unique = true)
    private String title;

    @NotNull
    @Column(nullable = false)
    private String location;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isConfigured() {
        return !getTitle().isEmpty() && !getLocation().isEmpty();
    }

    public Race(@NotNull Date date, @NotNull String title, @NotNull String location) {
        this.date = date;
        this.title = title;
        this.location = location;
    }

    protected Race() {
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
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
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
