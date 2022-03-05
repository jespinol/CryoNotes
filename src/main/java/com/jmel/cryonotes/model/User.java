package com.jmel.cryonotes.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    @NotBlank (message = "Email cannot be blank")
    private String email;

    @Column
    @NotBlank(message = "Password cannot be blank")
    @Size(min=6, max=64, message = "Password must contain at least 6 characters")
    private String password;

    @Column
    @NotBlank(message = "Name cannot be blank")
    private String firstName;

    @Column
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getId() {
        return id;
    }
}