package cz.muni.fi.pa165.dto.raceparticipation;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.dto.race.RaceDTO;

import java.util.Objects;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class SaveRaceParticipationDTO extends BaseDTO {

    private long carSetupId;
    private long driverId;
    private RaceDTO raceDTO;
    private int resultPosition = -1;

    public SaveRaceParticipationDTO(RaceParticipationDTO dto) {
        setId(dto.getId());
        setCarSetupId(dto.getCarSetup().getId());
        setDriverId(dto.getDriver().getId());
        setRaceDTO(dto.getRace());
        setResultPosition(dto.getResultPosition());
    }

    public SaveRaceParticipationDTO() {
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

    public RaceDTO getRaceDTO() {
        return raceDTO;
    }

    public void setRaceDTO(RaceDTO raceDTO) {
        this.raceDTO = raceDTO;
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
                && Objects.equals(raceDTO, that.raceDTO)
                && carSetupId == that.carSetupId;
    }

    @Override
    public int hashCode() {
        int result = (int) (carSetupId ^ (carSetupId >>> 32));
        result = 31 * result + (int) (driverId ^ (driverId >>> 32));
        result = 31 * result + raceDTO.hashCode();
        result = 31 * result + resultPosition;
        return result;
    }

    @Override
    public String toString() {
        return "RaceParticipationDTO{" +
                "carSetupId=" + carSetupId +
                ", driverId=" + driverId +
                ", raceId=" + raceDTO.toString() +
                ", resultPosition=" + resultPosition +
                "} " + super.toString();
    }
}
