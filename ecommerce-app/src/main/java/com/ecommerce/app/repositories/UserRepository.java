package com.ecommerce.app.repositories;

import com.ecommerce.app.models.Product;
import com.ecommerce.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

