package view.dto.request.user;

import persistence.entity.user.type.RoleType;
import view.dto.request.DtoRequest;

import java.util.Objects;

public class RoleDtoRequest extends DtoRequest {

    private RoleType roleType;

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDtoRequest that = (RoleDtoRequest) o;
        return roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleType);
    }

    @Override
    public String toString() {
        return "RoleDtoRequest{" +
                "roleType=" + roleType +
                '}';
    }
}