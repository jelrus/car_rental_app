package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class ManagerActions extends BaseEntity {

    private Long userId;
    private Long actionId;

    public ManagerActions() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return Objects.equals(userId, that.userId) &&
               Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, actionId);
    }

    @Override
    public String toString() {
        return "ManagerActions{" + super.toString() +
                "userId=" + userId +
                ", actionId=" + actionId +
                '}';
    }
}