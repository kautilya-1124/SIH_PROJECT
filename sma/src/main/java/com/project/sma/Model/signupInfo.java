package com.project.sma.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "signup_info")
public class signupInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;
    
    @Column(nullable = false, length = 10)
    private String phone;
    
    @Column(nullable = false, length = 50)
    private String email;
    
    @Column(nullable = false, length = 500)
    private String address;
    
    @Column(nullable = false, length = 6)
    private String pincode;

    @Column(length = 5000)
    private String profilePic;

    @Column(nullable = false, length = 100)
    private String password;  // added password

    @Transient
    private String confirmPassword;  // not stored in DB, only for validation

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getProfilePic() { return profilePic; }
    public void setProfilePic(String profilePic) { this.profilePic = profilePic; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
