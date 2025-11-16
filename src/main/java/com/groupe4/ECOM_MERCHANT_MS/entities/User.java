package com.groupe4.ECOM_MERCHANT_MS.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    @Nullable
    private LocalDateTime phone_verified_at;
    @Nullable
    private Boolean phone_verified;
    @Nullable
    private LocalDateTime updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Nullable
    public LocalDateTime getPhone_verified_at() {
        return phone_verified_at;
    }

    public void setPhone_verified_at(@Nullable LocalDateTime phone_verified_at) {
        this.phone_verified_at = phone_verified_at;
    }

    @Nullable
    public Boolean getPhone_verified() {
        return phone_verified;
    }

    public void setPhone_verified(@Nullable Boolean phone_verified) {
        this.phone_verified = phone_verified;
    }

    @Nullable
    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(@Nullable LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
