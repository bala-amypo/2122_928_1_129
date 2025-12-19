package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "users",   // "user" is reserved in some DBs
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
    }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email format is invalid")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 255, message = "Password must be at least 8 characters")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Role must not be blank")
    @Column(nullable = false, length = 50)
    private String role;

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email.toLowerCase(); // normalize
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // hash in service layer
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
