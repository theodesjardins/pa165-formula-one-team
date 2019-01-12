package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.enums.ComponentType;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author Théo Desjardins
 */
public class ComponentDTO extends BaseDTO {

    private ComponentType type;
    @NotBlank()
    private String name;
    @Valid()
    private List<ComponentParameterDTO> parameters;

    public ComponentType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public List<ComponentParameterDTO> getParameters() {
        return this.parameters;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(List<ComponentParameterDTO> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentDTO)) return false;
        ComponentDTO that = (ComponentDTO) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, parameters);
    }
}
