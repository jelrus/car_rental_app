package persistence.entity.relation;

import persistence.entity.BaseEntity;
import persistence.entity.annotations.Column;
import persistence.entity.annotations.MergeField;
import persistence.entity.annotations.Table;

import java.util.Objects;

@Table(tableName = "manager_actions")
public class ManagerActions extends BaseEntity {

    @Column(name = "manager_id")
    private Long managerId;

    @MergeField(on = "action_id")
    @Column(name = "action_id")
    private Long actionId;

    public ManagerActions() {
        super();
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ManagerActions that = (ManagerActions) o;
        return Objects.equals(managerId, that.managerId) && Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerId, actionId);
    }

    @Override
    public String toString() {
        return "ManagerActions{" + super.toString() + " " +
                "managerId=" + managerId +
                ", actionId=" + actionId +
                '}';
    }
}
