package persistence.entity.user;

import persistence.entity.BaseUser;
import persistence.entity.user.type.UserRole;

public class Admin extends BaseUser {

    public Admin() {
        super();
        setRoleType(UserRole.ROLE_ADMIN);
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