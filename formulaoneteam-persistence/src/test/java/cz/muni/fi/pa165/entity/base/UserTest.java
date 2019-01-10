package cz.muni.fi.pa165.entity.base;

import cz.muni.fi.pa165.dao.base.BaseTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class UserTest extends BaseTest {

    private User user;
    
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Before
    public void setUp() {
        user = new User() {};
    }

    @Test
    public void whenSomeFieldsAreNotSet_validationFails() {

        //given
        user.setId(1);

        //then
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        //given
        user.setName("name");

        //then
        violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        //given
        user.setSurname("surname");

        //then
        violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        //given
        user.setEmail("email");

        //then
        violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenAllFieldsAreSet_validationPasses() {
        //when
        user.setId(1);
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email@email.sk");
        user.setPassword("password");

        //then
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(User.class)
                .withOnlyTheseFields("email")
                .withNonnullFields("email")
                .verify();
    }
}
