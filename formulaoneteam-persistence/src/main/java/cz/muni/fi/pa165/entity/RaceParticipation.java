package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.Objects;

/**
 * @author Adel Chakouri
 */
@Entity
public class RaceParticipation extends BaseEntity {

    public static final int NO_RESULT_POSITION = -1;

    @NotNull
    @ManyToOne
    private CarSetup carSetup;

    @NotNull
    @ManyToOne
    private Driver driver;

    @NotNull
    @ManyToOne
    private Race race;

    @Column(nullable = false)
    private int resultPosition;

    public RaceParticipation(@NotNull CarSetup carSetup, @NotNull Driver driver, @NotNull Race race, int resultPosition) {
        this.carSetup = carSetup;
        this.driver = driver;
        this.race = race;
        this.resultPosition = resultPosition;
    }

    protected RaceParticipation() {
    }

    @NotNull
    public CarSetup getCarSetup() {
        return carSetup;
    }

    public void setCarSetup(@NotNull CarSetup car) {
        this.carSetup = car;
    }

    @NotNull
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(@NotNull Driver driver) {
        this.driver = driver;
    }

    @NotNull
    public Race getRace() {
        return race;
    }

    public void setRace(@NotNull Race race) {
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
        if (!(o instanceof RaceParticipation)) return false;
        RaceParticipation that = (RaceParticipation) o;
        return Objects.equals(carSetup, that.carSetup) &&
                Objects.equals(getDriver(), that.getDriver()) &&
                Objects.equals(getRace(), that.getRace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(carSetup, getDriver(), getRace());
    }

    @Override
    public String toString() {
        return "RaceParticipation{" +
                "car='" + getCarSetup() + '\'' +
                ", driver='" + getDriver() + '\'' +
                ", race='" + getRace() + '\'' +
                ",  resultPosition='" + getResultPosition() + '\'' +
                "} " + super.toString();
    }
}
