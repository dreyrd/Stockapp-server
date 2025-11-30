package br.com.ifsp.StockApp.model.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findAllByEnableTrue(Pageable pageable);
    Optional<User> findByEmail(String email);
}
