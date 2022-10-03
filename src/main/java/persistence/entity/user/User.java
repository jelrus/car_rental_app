package persistence.entity.user;

import persistence.entity.BaseUser;
import persistence.entity.user.type.UserRole;

public class User extends BaseUser {

    public User() {
        super();
        setRoleType(UserRole.ROLE_USER);
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
