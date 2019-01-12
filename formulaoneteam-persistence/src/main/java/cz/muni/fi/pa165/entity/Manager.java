package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Entity
public class Manager extends User {

    public Manager(@NotBlank String name, @NotBlank String surname, @NotBlank @Email String email) {
        super(name, surname, email);
    }

    protected Manager() {
        super();
    }
}
