package cz.muni.fi.pa165.enums;

/**
 * Enumeration containing component types.
 *
 * @author Ivan Dendis
 */
public enum ComponentType {
    ENGINE("Engine"),
    SUSPENSION("Suspension"),
    BRAKES("Brakes"),
    TRANSMISSION("Transmission"),
    TIRES("Tires"),
    COVER("Cover");

    private String type;

    ComponentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
