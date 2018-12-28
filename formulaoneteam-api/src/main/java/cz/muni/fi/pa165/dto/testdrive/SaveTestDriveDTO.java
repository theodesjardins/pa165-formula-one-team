package cz.muni.fi.pa165.dto.testdrive;

import cz.muni.fi.pa165.dto.base.BaseDTO;

import java.util.Date;
import java.util.Objects;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class SaveTestDriveDTO extends BaseDTO {

    private long carSetupId;
    private long driverId;
    private String notes;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCarSetupId() {
        return carSetupId;
    }

    public void setCarSetupId(long carSetupId) {
        this.carSetupId = carSetupId;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveTestDriveDTO)) return false;

        SaveTestDriveDTO that = (SaveTestDriveDTO) o;

        return Objects.equals(date, that.date)
                && Objects.equals(notes, that.notes)
                && carSetupId == that.carSetupId
                && driverId == that.driverId;
    }

    @Override
    public int hashCode() {
        int result = (int) (carSetupId ^ (carSetupId >>> 32));
        result = 31 * result + (int) (driverId ^ (driverId >>> 32));
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestDriveDTO{" +
                "carSetupId=" + carSetupId +
                ", driverId=" + driverId +
                ", notes='" + notes + '\'' +
                ", date=" + date +
                "} " + super.toString();
    }
}
