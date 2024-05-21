package ch.hevs.businessobject;

import java.time.LocalDate;
import java.time.Period;
import jakarta.persistence.*;

/**
 * This class represents an account and it used to store 
 * the information of a person in other classes with inheritance.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String email;
    @Column(nullable = true)
    private LocalDate birthdate;

    /**
     * Constructor for creating an Account object.
     */
    public Account() {
    }

    /**
     * Constructor for creating an Account object with specified properties.
     *
     * @param username  the username of the account
     * @param firstname the first name of the account holder
     * @param lastname  the last name of the account holder
     * @param address   the address of the account holder
     * @param phone     the phone number of the account holder
     * @param email     the email address of the account holder
     * @param birthdate the birthdate of the account holder
     */
    public Account(String username, String firstname, String lastname, String address, String phone, String email, LocalDate birthdate) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
    }

    /**
     * Get the ID of the account.
     *
     * @return the ID of the account
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the first name of the account holder.
     *
     * @return the first name of the account holder
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the first name of the account holder.
     *
     * @param firstname the first name to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Get the last name of the account holder.
     *
     * @return the last name of the account holder
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the last name of the account holder.
     *
     * @param lastname the last name to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get the address of the account holder.
     *
     * @return the address of the account holder
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the account holder.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the phone number of the account holder.
     *
     * @return the phone number of the account holder
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the phone number of the account holder.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the email address of the account holder.
     *
     * @return the email address of the account holder
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the account holder.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the birthdate of the account holder.
     *
     * @return the birthdate of the account holder
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Set the birthdate of the account holder.
     *
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Get the full name of the account holder.
     *
     * @return the full name of the account holder
     */
    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }

    /**
     * Set the ID of the account.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the username of the account.
     *
     * @return the username of the account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the account.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the age of the account holder.
     */
    public int getAge() {
        if (birthdate != null) {
            return Period.between(birthdate, LocalDate.now()).getYears();
        } else {
            throw new IllegalStateException("Birthdate is not set");
        }
    }
}