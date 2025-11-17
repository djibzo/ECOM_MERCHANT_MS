package com.groupe4.ECOM_MERCHANT_MS.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String phone;
    @Column(unique=true)
    private String nin;
    @Nullable
    private LocalDateTime phone_verified_at;
    @Nullable
    private Boolean phone_verified;
    @Nullable
    private LocalDateTime updated_at;

}
