package view.dto.request.register;

import java.util.Objects;

public class AuthDto {

    private String username;
    private String password;
    private String passwordConfirm;

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthDto authDto = (AuthDto) o;
        return Objects.equals(username, authDto.username) &&
               Objects.equals(password, authDto.password) &&
               Objects.equals(passwordConfirm, authDto.passwordConfirm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, passwordConfirm);
    }

    @Override
    public String toString() {
        return "AuthDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }
}
