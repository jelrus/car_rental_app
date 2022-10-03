package persistence.entity.user;

import persistence.entity.BaseUser;
import persistence.entity.user.type.UserRole;

public class Manager extends BaseUser {

    public Manager() {
        super();
        setRoleType(UserRole.ROLE_MANAGER);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
