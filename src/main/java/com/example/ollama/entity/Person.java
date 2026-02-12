package com.example.ollama.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a Person in the database.
 * Maps to the 'Persons' table in the 'public' schema.
 */
@Entity
@Table(name = "persons", schema = "public")
public class Person {

    @Id
    @Column(name = "personid")
    private Integer personId;

    @Column(name = "lastname", length = 255)
    private String lastName;

    @Column(name = "firstname", length = 255)
    private String firstName;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "city", length = 255)
    private String city;

    // Default constructor
    public Person() {
    }

    // Constructor with all fields
    public Person(Integer personId, String lastName, String firstName, String address, String city) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.city = city;
    }

    // Getters and Setters
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
