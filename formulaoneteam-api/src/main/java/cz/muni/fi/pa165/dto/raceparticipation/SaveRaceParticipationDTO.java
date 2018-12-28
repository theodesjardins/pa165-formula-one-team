package cz.muni.fi.pa165.dto.raceparticipation;

import cz.muni.fi.pa165.dto.base.BaseDTO;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class SaveRaceParticipationDTO extends BaseDTO {

    private long carSetupId;
    private long driverId;
    private long raceId;
    private int resultPosition;

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

    public long getRaceId() {
        return raceId;
    }

    public void setRaceId(long raceId) {
        this.raceId = raceId;
    }

    public int getResultPosition() {
        return resultPosition;
    }

    public void setResultPosition(int resultPosition) {
        this.resultPosition = resultPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveRaceParticipationDTO)) return false;

        SaveRaceParticipationDTO that = (SaveRaceParticipationDTO) o;

        return driverId == that.driverId
                && raceId == that.raceId
                && carSetupId == that.carSetupId;
    }

    @Override
    public int hashCode() {
        int result = (int) (carSetupId ^ (carSetupId >>> 32));
        result = 31 * result + (int) (driverId ^ (driverId >>> 32));
        result = 31 * result + (int) (raceId ^ (raceId >>> 32));
        result = 31 * result + resultPosition;
        return result;
    }

    @Override
    public String toString() {
        return "RaceParticipationDTO{" +
                "carSetupId=" + carSetupId +
                ", driverId=" + driverId +
                ", raceId=" + raceId +
                ", resultPosition=" + resultPosition +
                "} " + super.toString();
    }
}
