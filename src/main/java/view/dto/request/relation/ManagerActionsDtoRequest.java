package view.dto.request.relation;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class ManagerActionsDtoRequest extends DtoRequest {

    private Long userId;
    private Long actionId;

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
        ManagerActionsDtoRequest that = (ManagerActionsDtoRequest) o;
        return Objects.equals(userId, that.userId) && Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, actionId);
    }

    @Override
    public String toString() {
        return "ManagerActionsDtoRequest{" +
                "userId='" + userId + '\'' +
                ", actionId='" + actionId + '\'' +
                '}';
    }
}