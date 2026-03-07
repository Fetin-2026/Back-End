package com.fetin2026.whtie_label.repository;

import com.fetin2026.whtie_label.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

}
