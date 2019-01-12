package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

/**
 * @author Ivan Dendis
 */
public class ComponentParameterDTO extends BaseDTO {

    @NotBlank()
    private String name;
    @NotBlank()
    private String value;

    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentParameterDTO)) return false;
        ComponentParameterDTO that = (ComponentParameterDTO) o;
        return that.getValue().equals(getValue()) &&
                that.getName().equals(getName()); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getName());
    }

    @Override
    public String toString() {
        return "ComponentParameterDTO{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                "} " + super.toString();
    }
}
