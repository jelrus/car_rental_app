package view.dto.response.relation;

import persistence.entity.relation.ManagerActions;
import view.dto.response.DtoResponse;

import java.util.Date;
import java.util.Objects;

public class ManagerActionsDtoResponse extends DtoResponse {

    private Long managerId;
    private Long actionId;

    public ManagerActionsDtoResponse(ManagerActions mActs) {
        super(mActs.getId(), mActs.getCreated(), mActs.getUpdated(), mActs.getVisible());
        setManagerId(mActs.getManagerId());
        setActionId(mActs.getActionId());
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
        ManagerActionsDtoResponse that = (ManagerActionsDtoResponse) o;
        return Objects.equals(managerId, that.managerId) && Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, actionId);
    }

    @Override
    public String toString() {
        return "ManagerActionsDtoResponse{" + super.toString() +
                "managerId=" + managerId +
                ", actionId=" + actionId +
                '}';
    }
}
