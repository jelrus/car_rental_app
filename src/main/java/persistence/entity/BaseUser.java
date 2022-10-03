package persistence.entity;

import persistence.entity.product.Order;
import persistence.entity.user.type.UserRole;

import java.util.Collection;
import java.util.Objects;

public abstract class BaseUser extends BaseEntity{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePic;
    private String description;
    private Boolean enabled;
    private UserRole roleType;
    private Collection<Order> orders;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public UserRole getRoleType() {
        return roleType;
    }

    public void setRoleType(UserRole roleType) {
        this.roleType = roleType;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseUser baseUser = (BaseUser) o;
        return Objects.equals(username, baseUser.username) &&
               Objects.equals(password, baseUser.password) &&
               Objects.equals(firstName, baseUser.firstName) &&
               Objects.equals(lastName, baseUser.lastName) &&
               Objects.equals(profilePic, baseUser.profilePic) &&
               Objects.equals(description, baseUser.description) &&
               Objects.equals(enabled, baseUser.enabled) &&
               roleType == baseUser.roleType &&
               Objects.equals(orders, baseUser.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, firstName, lastName,
                            profilePic, description, enabled, roleType, orders);
    }
}
