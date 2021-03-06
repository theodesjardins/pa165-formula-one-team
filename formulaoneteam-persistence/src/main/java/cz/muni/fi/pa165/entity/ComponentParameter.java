package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Class representing a component parameter entity.
 *
 * @author Ivan Dendis
 */
@Entity
public class ComponentParameter extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name = "";

    @NotBlank
    @Column
    private String value = "";

    @ManyToOne(cascade = CascadeType.ALL)
    private Component component;

    public ComponentParameter(@NotBlank String name, @NotBlank String value) {
        this.name = name;
        this.value = value;
    }

    protected ComponentParameter() {
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    @NotBlank
    public String getValue() {
        return value;
    }

    public void setValue(@NotBlank String value) {
        this.value = value;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentParameter)) return false;
        ComponentParameter that = (ComponentParameter) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValue());
    }

    @Override
    public String toString() {
        return "ComponentParameter{" + "name='" + getName() + '\'' + ", value='" + getValue() + '\'' + "} "
                + super.toString();
    }

}
