package cz.muni.fi.pa165.entity.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@MappedSuperclass
public abstract class BaseEntity {

    public static long NO_ID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    public long getId() {
        return id == null ? NO_ID : id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasId() {
        return getId() != NO_ID;
    }

    @Override
    public String toString() {
        return "BaseEntity{id=" + getId() + '}';
    }
}
