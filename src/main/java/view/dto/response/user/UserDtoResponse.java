package view.dto.response.user;

import persistence.entity.user.BaseUser;
import view.dto.response.DtoResponse;

import java.math.BigDecimal;
import java.util.Objects;

public class UserDtoResponse extends DtoResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String profilePic;
    private BigDecimal balance;
    private String description;

    public UserDtoResponse(BaseUser baseUser) {
        super(baseUser.getId(), baseUser.getCreated(), baseUser.getUpdated(), baseUser.getVisible());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDtoResponse that = (UserDtoResponse) o;
        return Objects.equals(username, that.username) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(profilePic, that.profilePic) &&
               Objects.equals(balance, that.balance) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, profilePic, balance, description);
    }

    @Override
    public String toString() {
        return "UserDtoResponse{" + super.toString() +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", balance=" + balance +
                ", description='" + description + '\'' +
                '}';
    }
}
