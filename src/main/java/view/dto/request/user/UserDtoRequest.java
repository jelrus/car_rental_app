package view.dto.request.user;

import persistence.entity.user.type.RoleType;
import view.dto.request.DtoRequest;

import java.math.BigDecimal;
import java.util.Objects;

public class UserDtoRequest extends DtoRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePic;
    private BigDecimal balance;
    private String description;
    private Boolean enabled;
    private RoleType roleType;

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
        UserDtoRequest that = (UserDtoRequest) o;
        return Objects.equals(username, that.username) &&
               Objects.equals(password, that.password) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(profilePic, that.profilePic) &&
               Objects.equals(balance, that.balance) &&
               Objects.equals(description, that.description) &&
               Objects.equals(enabled, that.enabled) &&
               roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, profilePic, balance, description, enabled, roleType);
    }

    @Override
    public String toString() {
        return "UserDtoRequest{" +
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