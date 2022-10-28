package persistence.entity.interaction;

import persistence.entity.BaseEntity;

import java.util.Date;
import java.util.Objects;

public class Passport extends BaseEntity {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String country;
    private String zipCode;
    private String region;
    private String city;
    private String street;
    private String building;
    private String phoneNumber;
    private String email;

    public Passport() {
        super();
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Passport passport = (Passport) o;
        return Objects.equals(firstName, passport.firstName) &&
               Objects.equals(lastName, passport.lastName) &&
               Objects.equals(birthDate, passport.birthDate) &&
               Objects.equals(country, passport.country) &&
               Objects.equals(zipCode, passport.zipCode) &&
               Objects.equals(region, passport.region) &&
               Objects.equals(city, passport.city) &&
               Objects.equals(street, passport.street) &&
               Objects.equals(building, passport.building) &&
               Objects.equals(phoneNumber, passport.phoneNumber) &&
               Objects.equals(email, passport.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, birthDate, country, zipCode,
                            region, city, street, building, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Passport{" + super.toString() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}