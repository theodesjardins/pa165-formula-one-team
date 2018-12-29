package cz.muni.fi.pa165.dto.testdrive;

import java.util.Objects;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class SaveTestDriveDTO extends BaseTestDriveDTO {

    private long carSetupId;
    private long driverId;

    public SaveTestDriveDTO(TestDriveDTO testDriveDTO) {
        setId(testDriveDTO.getId());
        setDate(testDriveDTO.getDate());
        setNotes(testDriveDTO.getNotes());
        setCarSetupId(testDriveDTO.getCarSetup().getId());
        setDriverId(testDriveDTO.getDriver().getId());
    }

    public SaveTestDriveDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveTestDriveDTO)) return false;

        SaveTestDriveDTO that = (SaveTestDriveDTO) o;

        return Objects.equals(getDate(), that.getDate())
                && Objects.equals(getNotes(), that.getNotes())
                && carSetupId == that.carSetupId
                && driverId == that.driverId;
    }

    @Override
    public int hashCode() {
        int result = (int) (carSetupId ^ (carSetupId >>> 32));
        result = 31 * result + (int) (driverId ^ (driverId >>> 32));
        result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SaveTestDriveDTO{" +
                "carSetupId=" + carSetupId +
                ", driverId=" + driverId +
                ", notes='" + getNotes() + '\'' +
                ", date=" + getDate() +
                "} " + super.toString();
    }
}
