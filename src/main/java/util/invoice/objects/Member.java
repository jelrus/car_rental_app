package util.invoice.objects;

import java.util.Objects;

public class Member {

    private String name;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String building;
    private String phone;
    private String email;

    public Member() {
    }

    public String getGlobalAddress(){
        return this.country + ", " + this.zipCode + ", " + this.city;
    }

    public String getLocalAddress(){
        return this.street + ", " + this.building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        Member member = (Member) o;
        return Objects.equals(name, member.name) && Objects.equals(country, member.country) &&
               Objects.equals(zipCode, member.zipCode) && Objects.equals(city, member.city) &&
               Objects.equals(street, member.street) && Objects.equals(building, member.building) &&
               Objects.equals(phone, member.phone) && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, zipCode, city, street, building, phone, email);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
