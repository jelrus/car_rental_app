package persistence.entity.user.impl;

import persistence.entity.user.BaseUser;
import persistence.entity.user.type.UserRole;

public class Admin extends BaseUser {

    public Admin() {
        super();
        setRoleType(UserRole.ADMIN);
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