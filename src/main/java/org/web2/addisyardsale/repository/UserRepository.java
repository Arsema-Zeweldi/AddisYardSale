package org.web2.addisyardsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web2.addisyardsale.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String Email, String Password);

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);
}
