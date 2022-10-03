package persistence.entity;

import java.util.Date;
import java.util.Objects;

public abstract class BaseEntity {

    private Long id;
    private Date created;
    private Date updated;
    private Boolean visible;

    public BaseEntity() {
        this.created = new Date();
        this.updated = new Date();
        this.visible = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(created, that.created) &&
               Objects.equals(updated, that.updated) &&
               Objects.equals(visible, that.visible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, updated, visible);
    }
}
