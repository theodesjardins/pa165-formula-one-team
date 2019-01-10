package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Théo Desjardins
 */
@Entity
public class CarSetup extends BaseEntity {

    @NotNull
    @ManyToOne
    private Component engine;

    @NotNull
    @ManyToOne
    private Component suspension;

    @NotNull
    @ManyToOne
    private Component brakes;

    @NotNull
    @ManyToOne
    private Component transmission;

    @NotNull
    @ManyToOne
    private Component tires;

    @NotNull
    @ManyToOne
    private Component cover;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carSetup", fetch = FetchType.EAGER)
    private Set<RaceParticipation> raceParticipations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carSetup", fetch = FetchType.EAGER)
    private Set<TestDrive> testDrives = new HashSet<>();

    public CarSetup(@NotNull Component engine, @NotNull Component suspension, @NotNull Component brakes,
            @NotNull Component transmission, @NotNull Component tires, @NotNull Component cover) {
        this.engine = engine;
        this.suspension = suspension;
        this.brakes = brakes;
        this.transmission = transmission;
        this.tires = tires;
        this.cover = cover;
    }

    protected CarSetup() {
    }

    public Set<RaceParticipation> getRaceParticipations() {
        return raceParticipations;
    }

    public void setRaceParticipations(Set<RaceParticipation> raceParticipations) {
        this.raceParticipations = raceParticipations;
    }

    public Set<TestDrive> getTestDrives() {
        return testDrives;
    }

    public void setTestDrives(Set<TestDrive> testDrives) {
        this.testDrives = testDrives;
    }

    @NotNull
    public Component getEngine() {
        return engine;
    }

    @NotNull
    public Component getSuspension() {
        return suspension;
    }

    @NotNull
    public Component getBrakes() {
        return brakes;
    }

    @NotNull
    public Component getTransmission() {
        return transmission;
    }

    @NotNull
    public Component getTires() {
        return tires;
    }

    @NotNull
    public Component getCover() {
        return cover;
    }

    public void setEngine(@NotNull Component engine) {
        this.engine = engine;
    }

    public void setSuspension(@NotNull Component suspension) {
        this.suspension = suspension;
    }

    public void setBrakes(@NotNull Component brakes) {
        this.brakes = brakes;
    }

    public void setTransmission(@NotNull Component transmission) {
        this.transmission = transmission;
    }

    public void setTires(@NotNull Component tires) {
        this.tires = tires;
    }

    public void setCover(@NotNull Component cover) {
        this.cover = cover;
    }

    public List<Component> getComponents() {
        return Arrays.asList(getEngine(), getBrakes(), getCover(), getSuspension(), getTransmission(), getTires());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CarSetup))
            return false;
        CarSetup carSetup = (CarSetup) o;
        return Objects.equals(getEngine(), carSetup.getEngine())
                && Objects.equals(getSuspension(), carSetup.getSuspension())
                && Objects.equals(getBrakes(), carSetup.getBrakes())
                && Objects.equals(getTransmission(), carSetup.getTransmission())
                && Objects.equals(getTires(), carSetup.getTires()) && Objects.equals(getCover(), carSetup.getCover());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEngine(), getSuspension(), getBrakes(), getTransmission(), getTires(), getCover());
    }

    public String toString() {
        return "CarSetup{" + "engine='" + getEngine() + '\'' + ", suspension='" + getSuspension() + '\'' + ", brakes='"
                + getBrakes() + '\'' + ", transmission='" + getTransmission() + '\'' + ", tires='" + getTires() + '\''
                + ", cover='" + getCover() + '\'' + "} " + super.toString();
    }
}
