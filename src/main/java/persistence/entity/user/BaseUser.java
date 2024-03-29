package persistence.entity.user;

import persistence.entity.BaseEntity;
import persistence.entity.user.type.RoleType;

import java.math.BigDecimal;
import java.util.Objects;

public class BaseUser extends BaseEntity {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePic;
    private BigDecimal balance;
    private String description;
    private Boolean enabled;
    private RoleType roleType;

    public BaseUser() {
        super();
        this.enabled = true;
    }

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
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
               Objects.equals(balance, baseUser.balance) &&
               Objects.equals(description, baseUser.description) &&
               Objects.equals(enabled, baseUser.enabled) &&
               roleType == baseUser.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, firstName, lastName, profilePic,
                            balance, description, enabled, roleType);
    }

    @Override
    public String toString() {
        return "BaseUser{" + super.toString() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", balance=" + balance +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                ", roleType=" + roleType +
                '}';
    }
}