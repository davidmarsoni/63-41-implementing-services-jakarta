package ch.hevs.businessobject;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Person
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
    private String firstname;
    private String lastname;
    private String address;
    private String phone;

    private String email;

    @Column(nullable = true)
    private Date birthdate;

    public Person() {
    }

    public Person(String firstname, String lastname, String address, String phone, String email, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
    }

    /**
     * Get the first name of the person.
     * 
     * @return String the first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the first name of the person.
     * 
     * @param firstname the first name to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Get the last name of the person.
     * 
     * @return String the last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the last name of the person.
     * 
     * @param lastname the last name to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get the address of the person.
     * 
     * @return String the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the person.
     * 
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the phone number of the person.
     * 
     * @return String the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the phone number of the person.
     * 
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the email address of the person.
     * 
     * @return String the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the person.
     * 
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the birthdate of the person.
     * 
     * @return Date the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Set the birthdate of the person.
     * 
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }

    @Override
    public String toString() {
        return "Person [address=" + address + ", birthdate=" + birthdate + ", email=" + email + ", firstname="
                + firstname + ", lastname=" + lastname + ", phone=" + phone + "]";
    }
}