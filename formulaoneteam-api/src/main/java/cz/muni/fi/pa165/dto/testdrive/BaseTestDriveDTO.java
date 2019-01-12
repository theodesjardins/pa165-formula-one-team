package cz.muni.fi.pa165.dto.testdrive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.utils.DateUtils;

import java.util.Date;

import javax.validation.constraints.NotBlank;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseTestDriveDTO extends BaseDTO {

    @NotBlank
    private String notes;
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setFormattedDate(String formattedDate) {
        this.date = DateUtils.parseDate(formattedDate);
    }

    @JsonIgnore
    public String getFormattedDate() {
        return DateUtils.getFormattedDate(date);
    }
}
