package cz.muni.fi.pa165.sample.data;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.date.DateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static cz.muni.fi.pa165.entity.RaceParticipation.NO_RESULT_POSITION;
import static cz.muni.fi.pa165.enums.CharacteristicsType.*;
import static cz.muni.fi.pa165.enums.ComponentType.*;
import static cz.muni.fi.pa165.enums.EngineerSpecialization.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@Service
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Inject
    private CarSetupService carSetupService;

    @Inject
    private CharacteristicsValueService characteristicsValueService;

    @Inject
    private ComponentService componentService;

    @Inject
    private ComponentParameterService componentParameterService;

    @Inject
    private DriverService driverService;

    @Inject
    private EngineerService engineerService;

    @Inject
    private ManagerService managerService;

    @Inject
    private RaceService raceService;

    @Inject
    private RaceParticipationService raceParticipationService;

    @Inject
    private TestDriveService testDriveService;

    @Inject
    private DateService dateService;

    @Override
    public void loadData() {
        managerService.register(new Manager("Ross", "Parker", "ross@parker.com"), "p4ssword");

        engineerService.register(new Engineer("Sun", "Li", "sun@engineers.com", AERODYNAMICS), "my-dirty-secret");
        engineerService.register(new Engineer("Tommy", "Lee", "Tommy@engineers.com", ENGINES), "thisissafepassword");
        engineerService.register(new Engineer("Orson", "Wells", "Orson@engineers.com", FLUID_MECHANICS), "bestEng1neer");
        engineerService.register(new Engineer("Karen", "Gillian", "Karen@engineers.com", EMISSIONS), "helloIamCaren");
        engineerService.register(new Engineer("Robert", "Ford", "Robert@engineers.com", THERMODYNAMICS), "escort");

        final Driver fernardoAlonso = driverService.register(
                new Driver(
                        "Fernando",
                        "Alonso",
                        "fernardo@alonso.cz",
                        "Spanish",
                        dateService.createDate(29, 6, 1981),
                        DriverStatus.MAIN,
                        characteristicsValueService.add(newHashSet(
                                new CharacteristicsValue(AGGRESSIVITY, 100),
                                new CharacteristicsValue(STEERING, 50),
                                new CharacteristicsValue(DRIVING_ON_WET, 15),
                                new CharacteristicsValue(ENDURANCE, 4),
                                new CharacteristicsValue(PATIENCE, 8)
                        ))
                ),
                "fernardoIsNumber1"
        );

        final Driver michaelSchumacher = driverService.register(
                new Driver(
                        "Michael",
                        "Schumacher",
                        "micheal@schumacher.cz",
                        "German",
                        dateService.createDate(3, 1, 1969),
                        DriverStatus.MAIN,
                        characteristicsValueService.add(newHashSet(
                                new CharacteristicsValue(AGGRESSIVITY, 40),
                                new CharacteristicsValue(STEERING, 100),
                                new CharacteristicsValue(DRIVING_ON_WET, 20),
                                new CharacteristicsValue(ENDURANCE, 60),
                                new CharacteristicsValue(PATIENCE, 8)
                        ))
                ),
                "ILoveGermany"
        );

        final Driver testDriver1 = driverService.register(
                new Driver(
                        "Miki",
                        "Raikonen",
                        "miki@raikonen.fi",
                        "Finnish",
                        dateService.createDate(3, 1, 1995),
                        DriverStatus.TEST,
                        characteristicsValueService.add(newHashSet(
                                new CharacteristicsValue(AGGRESSIVITY, 10),
                                new CharacteristicsValue(STEERING, 15),
                                new CharacteristicsValue(DRIVING_ON_WET, 20),
                                new CharacteristicsValue(ENDURANCE, 30),
                                new CharacteristicsValue(PATIENCE, 8)
                        ))

                ),
                "aslkfhfrer342"
        );

        final Driver testDriver2 = driverService.register(
                new Driver(
                        "John",
                        "Doe",
                        "john@doe.com",
                        "American",
                        dateService.createDate(4, 11, 1998),
                        DriverStatus.TEST,
                        characteristicsValueService.add(newHashSet(
                                new CharacteristicsValue(AGGRESSIVITY, 10),
                                new CharacteristicsValue(STEERING, 15),
                                new CharacteristicsValue(DRIVING_ON_WET, 20),
                                new CharacteristicsValue(ENDURANCE, 30),
                                new CharacteristicsValue(PATIENCE, 8)
                        ))
                ),
                "john-does-password"
        );

        final Component cosworthEngine = createComponent(
                "Cosworth DFV",
                ENGINE,
                newHashSet(
                        new ComponentParameter("Power", "440 kW"),
                        new ComponentParameter("Torque", "9,000 rpm")
                )
        );

        final Component tipo500Engine = createComponent(
                "Tipo 500",
                ENGINE,
                newHashSet(
                        new ComponentParameter("Power", "138 kW"),
                        new ComponentParameter("Torque", "4,000 rpm")
                )
        );

        final Component tipo375Engine = createComponent(
                "Tipo 375",
                ENGINE,
                newHashSet(
                        new ComponentParameter("Power", "164 kW"),
                        new ComponentParameter("Torque", "5,500 rpm")
                )
        );

        final Component aCDelcoBrake = createComponent(
                "ACDelco 171-1040 GM",
                BRAKES,
                newHashSet(new ComponentParameter("Material", "Friction-enhancing material"))
        );

        final Component boschBrake = createComponent(
                "Bosch BC905 QuietCast",
                BRAKES,
                newHashSet(new ComponentParameter("Material", "Ceramic"))
        );

        final Component bremboBrake = createComponent(
                "Brembo P83024N Rear",
                BRAKES,
                newHashSet(new ComponentParameter("Material", "Ceramic"))
        );

        final Component eBCBrake = createComponent(
                "EBC Brakes DP31210C",
                BRAKES,
                newHashSet(new ComponentParameter("Material", "Friction-enhancing material"))
        );

        final Component hawkBrake = createComponent(
                "Hawk HB453F.585 HPS",
                BRAKES,
                newHashSet(new ComponentParameter("Material", "Carbon Disc Brake"))
        );

        final Component satchellSuspension = createComponent(
                "Satchell link",
                SUSPENSION,
                newHashSet(new ComponentParameter("Type", "Dependent"))
        );

        final Component slidingPillarSuspension = createComponent(
                "Sliding pillar",
                SUSPENSION,
                newHashSet(new ComponentParameter("Type", "Independent"))
        );

        final Component multiLinkSuspension = createComponent(
                "Multi-link",
                SUSPENSION,
                newHashSet(new ComponentParameter("Type", "Independent"))
        );

        final Component cumminsTransmission = createComponent(
                "Cummins: 68 RFE",
                TRANSMISSION,
                newHashSet(
                        new ComponentParameter("Type", "Automatic"),
                        new ComponentParameter("Number of gears", "6")
                )
        );

        final Component cruiseTransmission = createComponent(
                "Cruise-O-Matic",
                TRANSMISSION,
                newHashSet(
                        new ComponentParameter("Type", "Automatic"),
                        new ComponentParameter("Number of gears", "3")
                )
        );

        final Component torqueFliteTransmission = createComponent(
                "TorqueFlite",
                TRANSMISSION,
                newHashSet(
                        new ComponentParameter("Type", "Automatic"),
                        new ComponentParameter("Number of gears", "8")
                )
        );

        final Component tires1 = createComponent(
                "Tires 1",
                TIRES,
                newHashSet(
                        new ComponentParameter("Manufacturer", "Michellin"),
                        new ComponentParameter("Nominal section width", "205mm")
                )
        );

        final Component tires2 = createComponent(
                "Tires 2",
                TIRES,
                newHashSet(
                        new ComponentParameter("Manufacturer", "Barum"),
                        new ComponentParameter("Nominal section width", "108mm")
                )
        );

        final Component cover = createComponent(
                "Carbon",
                COVER,
                newHashSet(new ComponentParameter("Reference area", "2800 ft*ft"))
        );

        CarSetup carSetup1 = carSetupService.add(
                new CarSetup(cosworthEngine, satchellSuspension, aCDelcoBrake, cumminsTransmission, tires1, cover)
        );
        CarSetup carSetup2 = carSetupService.add(
                new CarSetup(tipo500Engine, slidingPillarSuspension, boschBrake, cruiseTransmission, tires1, cover)
        );
        CarSetup carSetup3 = carSetupService.add(
                new CarSetup(tipo375Engine, multiLinkSuspension, bremboBrake, torqueFliteTransmission, tires2, cover)
        );

        Race pastRace = raceService.add(
                new Race(dateService.getPastDate(15), "WC Valencia", "Valencia")
        );
        Race futureRace = raceService.add(
                new Race(dateService.getFutureDate(30), "Germany World Championship", "Nuremberg")
        );

        raceParticipationService.add(newHashSet(
                new RaceParticipation(carSetup1, michaelSchumacher, pastRace, 1),
                new RaceParticipation(carSetup2, fernardoAlonso, pastRace, 5),
                new RaceParticipation(carSetup2, fernardoAlonso, futureRace, NO_RESULT_POSITION),
                new RaceParticipation(carSetup1, michaelSchumacher, futureRace, NO_RESULT_POSITION)
        ));

        testDriveService.add(newHashSet(
                new TestDrive(carSetup1, testDriver1, "Steering maybe too stiff.", dateService.getPastDate(40)),
                new TestDrive(carSetup1, testDriver2, "Great power.", dateService.getPastDate(40)),
                new TestDrive(carSetup2, testDriver1, "Pretty good steering.", dateService.getPastDate(30)),
                new TestDrive(carSetup2, testDriver2, "Did not finish, transmission broke.", dateService.getPastDate(30)),
                new TestDrive(carSetup3, testDriver1, "Fast, but missing some advantages of no. 2.", dateService.getPastDate(20)),
                new TestDrive(carSetup3, testDriver2, "Pretty fast.", dateService.getPastDate(20)),
                new TestDrive(carSetup3, testDriver2, "Slides a lot on wet.", dateService.getPastDate(5))
        ));
    }

    private Component createComponent(String name, ComponentType type, Set<ComponentParameter> parameters) {
        final Component component = new Component(name, type);
        for (ComponentParameter parameter : parameters) {
            parameter.setComponent(component);
            componentParameterService.add(parameter);
            component.addParameter(parameter);
        }
        componentService.add(component);
        return component;
    }
}
