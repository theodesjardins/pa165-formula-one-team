package cz.muni.fi.pa165.entity.base;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@MappedSuperclass
public abstract class User extends BaseEntity {

    public final static String EMAIL_FIELD = "email";

    @NotBlank 
    @Column(nullable = false)
    private String name = "";

    @NotBlank 
    @Column(nullable = false)
    private String surname = "";

    @Email
    @NotBlank 
    @Column(nullable = false, unique = true)
    private String email = "";

    @NotBlank
    @Column(nullable = false)
    private String password = "";

    public User(@NotBlank String name, @NotBlank String surname, @NotBlank @Email String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    protected User() {
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    @NotBlank
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NotBlank String surname) {
        this.surname = surname;
    }

    @NotBlank 
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    @NotBlank
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + getName() + '\''
                + ", surname='" + getSurname() + '\''
                + ", email='" + getEmail() + '\''
                + ", password='" + getPassword() + '\''
                + "} " + super.toString();
    }
}
