package persistence.entity.user.impl;

import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;

public class Manager extends BaseUser {

    public Manager() {
        super();
        setRoleType(RoleType.ROLE_MANAGER);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Manager{" + super.toString() + "}";
    }
}