package persistence.entity.user.impl;

import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;

public class Admin extends BaseUser {

    public Admin() {
        super();
        setRoleType(RoleType.ROLE_ADMIN);
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
        return "Admin{" + super.toString() + "}";
    }
}