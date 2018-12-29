package cz.muni.fi.pa165.dto.driver;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.utils.DateUtils;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class SimpleDriverDTO extends UserDTO {

    @NotEmpty
    private String nationality;
    private Date birthday = new Date();
    private DriverStatus driverStatus;
    private List<CharacteristicsValueDTO> characteristics = new ArrayList<>();

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayString() {
        return DateUtils.getFormattedDate(birthday);
    }

    public void setBirthdayString(String birthdayString) {
        this.birthday = DateUtils.parseDate(birthdayString);
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public List<CharacteristicsValueDTO> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicsValueDTO> characteristics) {
        this.characteristics = characteristics;
    }
}
