package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Adel Chakouri
 */
@Entity
public class TestDrive extends BaseEntity {

    @ManyToOne
    private CarSetup car;

    @ManyToOne
    private Driver driver;

    @NotNull
    @Column(nullable = false)
    private String notes;

    public TestDrive(CarSetup car, Driver driver, @NotNull String notes) {
        this.car = car;
        this.driver = driver;
        this.notes = notes;
    }

    protected TestDrive() {
    }

    public CarSetup getCarSetup() {
        return car;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setCar(CarSetup car) {
        this.car = car;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isConfigured() {
        return !getNotes().isEmpty() && getCarSetup().isConfigured() && getDriver().isConfigured();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TestDrive)) return false;

        TestDrive testDrive = (TestDrive) o;

        if (getCarSetup() != null ? !getCarSetup().equals(testDrive.getCarSetup()) : testDrive.getCarSetup() != null) return false;
        if (getDriver() != null ? !getDriver().equals(testDrive.getDriver()) : testDrive.getDriver() != null)
            return false;
        return getNotes() != null ? getNotes().equals(testDrive.getNotes()) : testDrive.getNotes() == null;
    }

    @Override
    public int hashCode() {
        int result = getCarSetup() != null ? getCarSetup().hashCode() : 0;
        result = 31 * result + (getDriver() != null ? getDriver().hashCode() : 0);
        result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestDrive{" +
                "car='" + getCarSetup() + '\'' +
                ", driver='" + getDriver() + '\'' +
                ", notes='" + getNotes() + '\'' +
                "} " + super.toString();
    }
}
