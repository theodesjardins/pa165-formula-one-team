package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;

import javax.persistence.Column;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class Manager extends User {

    @Column
    private String someField;

    public String getSomeField() {
        return someField;
    }

    public void setSomeField(String someField) {
        this.someField = someField;
    }

    public void someAction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Manager that = (Manager) o;

        return getSomeField() != null ? getSomeField().equals(that.getSomeField()) : that.getSomeField() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSomeField() != null ? getSomeField().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "someField='" + someField + '\'' +
                "} " + super.toString();
    }
}
