package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import java.util.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Entity
public class Driver extends User {

    @NotBlank
    @Column(nullable = false)
    private String nationality;

    @Past
    @Basic
    @Column(nullable = false)
    @NotNull
    private Date birthday;

    @Column
    @Enumerated
    private DriverStatus driverStatus;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<CharacteristicsValue> characteristics = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Collection<RaceParticipation> raceParticipations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Collection<TestDrive> testDrives = new ArrayList<>();

    public Driver(@NotBlank String name, @NotBlank String surname, @Email String email, @NotBlank String nationality,
            @Past @NotNull Date birthday, DriverStatus driverStatus) {
        super(name, surname, email);
        this.nationality = nationality;
        this.birthday = birthday;
        this.driverStatus = driverStatus;
    }

    public Driver(@NotBlank String name, @NotBlank String surname, @NotBlank @Email String email, @NotBlank String nationality, 
            @Past @NotNull Date birthday, @NotNull DriverStatus driverStatus, Set<CharacteristicsValue> characteristics) {
        this(name, surname, email, nationality, birthday, driverStatus);
        this.characteristics = characteristics;
    }

    protected Driver() {
    }

    public Collection<RaceParticipation> getRaceParticipations() {
        return raceParticipations;
    }

    public void setRaceParticipations(Collection<RaceParticipation> raceParticipations) {
        this.raceParticipations = raceParticipations;
    }

    @NotBlank
    public String getNationality() {
        return nationality;
    }

    public void setNationality(@NotBlank String nationality) {
        this.nationality = nationality;
    }

    @Past
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(@Past Date birthday) {
        this.birthday = birthday;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Set<CharacteristicsValue> getCharacteristics() {
        return Collections.unmodifiableSet(characteristics);
    }

    public CharacteristicsValue getCharacteristicOfType(CharacteristicsType type) {
        return characteristics.stream().filter(characteristicsValue -> characteristicsValue.getType() == type)
                .findFirst().orElse(new CharacteristicsValue(type, 0));
    }

    public void addCharacteristic(CharacteristicsValue characteristicsValue) {
        characteristics.add(characteristicsValue);
    }

    public void addCharacteristics(Set<CharacteristicsValue> characteristicsValue) {
        characteristics.addAll(characteristicsValue);
    }

    public void removeCharacteristics(CharacteristicsValue characteristicsValue) {
        characteristics.remove(characteristicsValue);
    }

    public Collection<TestDrive> getTestDrives() {
        return testDrives;
    }

    public void setTestDrives(Collection<TestDrive> testDrives) {
        this.testDrives = testDrives;
    }

    @Override
    public String toString() {
        return "Driver{" + "nationality='" + getNationality() + '\'' + ", birthdate=" + getBirthday()
                + ", driverStatus=" + getDriverStatus() + ", characteristics=" + getCharacteristics() + "} "
                + super.toString();
    }
}
