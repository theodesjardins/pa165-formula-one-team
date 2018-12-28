package cz.muni.fi.pa165.dto.driver;

import cz.muni.fi.pa165.dto.raceparticipation.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.testdrive.TestDriveDTO;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class DriverDTO extends SimpleDriverDTO {

    private List<RaceParticipationDTO> raceParticipations;
    private List<TestDriveDTO> testDrives;

    public List<RaceParticipationDTO> getRaceParticipations() {
        return raceParticipations;
    }

    public void setRaceParticipations(List<RaceParticipationDTO> raceParticipations) {
        this.raceParticipations = raceParticipations;
    }

    public List<TestDriveDTO> getTestDrives() {
        return testDrives;
    }

    public void setTestDrives(List<TestDriveDTO> testDrives) {
        this.testDrives = testDrives;
    }
}
