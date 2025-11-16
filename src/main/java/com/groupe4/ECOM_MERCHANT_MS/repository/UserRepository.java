package com.groupe4.ECOM_MERCHANT_MS.repository;

import com.groupe4.ECOM_MERCHANT_MS.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
