package cz.muni.fi.pa165.dto.testdrive;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.SimpleDriverDTO;

import java.util.Objects;

/**
 * @author Adel Chakouri
 */
public class TestDriveDTO extends BaseTestDriveDTO {

    private CarSetupDTO carSetup;
    private SimpleDriverDTO driver;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestDriveDTO)) return false;

        TestDriveDTO that = (TestDriveDTO) o;

        return Objects.equals(getDate(), that.getDate())
                && Objects.equals(getCarSetup(), that.getCarSetup())
                && Objects.equals(getDriver(), that.getDriver())
                && Objects.equals(getNotes(), that.getNotes());
    }

    @Override
    public int hashCode() {
        int result = getCarSetup() != null ? getCarSetup().hashCode() : 0;
        result = 31 * result + (getDriver() != null ? getDriver().hashCode() : 0);
        result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestDriveDTO{" +
                "car=" + getCarSetup() +
                ", driver=" + getDriver() +
                ", notes='" + getNotes() + '\'' +
                ", date=" + getDate() +
                '}';
    }
}
