package persistence.entity.user.impl;

import persistence.entity.annotations.Table;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.UserRole;

@Table(tableName = "users")
public class User extends BaseUser {

    public User() {
        super();
        setRoleType(UserRole.USER);
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