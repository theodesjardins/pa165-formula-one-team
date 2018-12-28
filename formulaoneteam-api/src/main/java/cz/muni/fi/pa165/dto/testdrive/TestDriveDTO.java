package cz.muni.fi.pa165.dto.testdrive;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.SimpleDriverDTO;

import java.util.Date;
import java.util.Objects;

/**
 * @author Adel Chakouri
 */
public class TestDriveDTO extends BaseDTO {

    private CarSetupDTO carSetup;
    private SimpleDriverDTO driver;
    private String notes;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CarSetupDTO getCarSetup() {
        return carSetup;
    }

    public void setCarSetup(CarSetupDTO car) {
        this.carSetup = car;
    }

    public SimpleDriverDTO getDriver() {
        return driver;
    }

    public void setDriver(SimpleDriverDTO driver) {
        this.driver = driver;
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
        if (!(o instanceof TestDriveDTO)) return false;

        TestDriveDTO that = (TestDriveDTO) o;

        return Objects.equals(date, that.date)
                && Objects.equals(carSetup, that.carSetup)
                && Objects.equals(driver, that.driver)
                && Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        int result = carSetup != null ? carSetup.hashCode() : 0;
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestDriveDTO{" +
                "car=" + carSetup +
                ", driver=" + driver +
                ", notes='" + notes + '\'' +
                ", date=" + date +
                '}';
    }
}
