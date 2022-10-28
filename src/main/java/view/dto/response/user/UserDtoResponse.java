package view.dto.response.user;

import persistence.entity.user.BaseUser;
import persistence.entity.user.type.RoleType;
import view.dto.response.DtoResponse;

import java.math.BigDecimal;
import java.util.Objects;

public class UserDtoResponse extends DtoResponse {

    private String firstName;
    private String lastName;
    private String username;
    private String profilePic;
    private BigDecimal balance;
    private String description;
    private Boolean enabled;
    private RoleType roleType;

    public UserDtoResponse(BaseUser user) {
        super(user.getId(), user.getCreated(), user.getUpdated(), user.getVisible());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setUsername(user.getUsername());
        setProfilePic(user.getProfilePic());
        setBalance(user.getBalance());
        setDescription(user.getDescription());
        setEnabled(user.getEnabled());
        setRoleType(user.getRoleType());
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        UserDtoResponse that = (UserDtoResponse) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) &&
               Objects.equals(username, that.username) && Objects.equals(profilePic, that.profilePic) &&
               Objects.equals(balance, that.balance) && Objects.equals(description, that.description) &&
               Objects.equals(enabled, that.enabled) && roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, username, profilePic, balance, description, enabled, roleType);
    }

    @Override
    public String toString() {
        return "UserDtoResponse{" + super.toString() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", balance=" + balance +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                ", roleType=" + roleType +
                '}';
    }
}