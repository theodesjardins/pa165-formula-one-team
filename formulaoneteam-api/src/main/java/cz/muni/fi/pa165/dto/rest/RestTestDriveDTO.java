package cz.muni.fi.pa165.dto.rest;

import java.util.Date;

/**
 * @author Ivan Dendis
 */
public class RestTestDriveDTO {
    
    private Date date;
    private String notes;
    private long carId;
    private long driverId;

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the driverId
     */
    public long getDriverId() {
        return driverId;
    }

    /**
     * @param driverId the driverId to set
     */
    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    /**
     * @return the carId
     */
    public long getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(long carId) {
        this.carId = carId;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    
}