package cz.muni.fi.pa165.dto.race;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.utils.DateUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author Adel Chakouri
 */
public class RaceDTO extends BaseDTO {

    private Date date = new Date();
    private String title;
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

    @JsonIgnore
    public String getDateString() {
        return DateUtils.getFormattedDate(date);
    }

    public void setDateString(String birthdayString) {
        this.date = DateUtils.parseDate(birthdayString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RaceDTO)) return false;

        RaceDTO raceDTO = (RaceDTO) o;

        if (!Objects.equals(date, raceDTO.date)) return false;
        if (!Objects.equals(title, raceDTO.title)) return false;
        return Objects.equals(location, raceDTO.location);
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
