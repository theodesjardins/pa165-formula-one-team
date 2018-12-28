package cz.muni.fi.pa165.dto.raceparticipation;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.SimpleDriverDTO;
import cz.muni.fi.pa165.dto.race.RaceDTO;

import java.util.Objects;

/**
 * @author Adel Chakouri
 */
public class RaceParticipationDTO extends BaseDTO {

    private CarSetupDTO carSetup;
    private SimpleDriverDTO driver;
    private RaceDTO race;
    private int resultPosition;

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

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
        this.race = race;
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
        if (!(o instanceof RaceParticipationDTO)) return false;

        RaceParticipationDTO that = (RaceParticipationDTO) o;

        if (resultPosition != that.resultPosition) return false;
        if (!Objects.equals(carSetup, that.carSetup)) return false;
        if (!Objects.equals(driver, that.driver)) return false;
        return Objects.equals(race, that.race);
    }

    @Override
    public int hashCode() {
        int result = carSetup != null ? carSetup.hashCode() : 0;
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + resultPosition;
        return result;
    }

    @Override
    public String toString() {
        return "RaceParticipationDTO{" +
                "car=" + carSetup +
                ", driver=" + driver +
                ", race=" + race +
                ", resultPosition=" + resultPosition +
                '}';
    }
}