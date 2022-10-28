package persistence.entity.user.impl;

import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;

public class User extends BaseUser {

    public User() {
        super();
        setRoleType(RoleType.ROLE_USER);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +"}";
    }
}