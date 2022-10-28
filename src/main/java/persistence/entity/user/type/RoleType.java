package persistence.entity.user.type;

public enum RoleType {
    ROLE_ADMIN("Admin"),
    ROLE_MANAGER("Manager"),
    ROLE_USER("User");

    private final String roleType;

    RoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
}
