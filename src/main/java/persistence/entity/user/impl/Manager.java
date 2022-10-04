package persistence.entity.user.impl;

import persistence.entity.user.BaseUser;
import persistence.entity.user.type.UserRole;

public class Manager extends BaseUser {

    public Manager() {
        super();
        setRoleType(UserRole.MANAGER);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}