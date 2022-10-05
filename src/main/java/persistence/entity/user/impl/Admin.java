package persistence.entity.user.impl;

import persistence.entity.annotations.Table;
import persistence.entity.user.BaseUser;
import persistence.entity.user.type.UserRole;

@Table(tableName = "users")
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