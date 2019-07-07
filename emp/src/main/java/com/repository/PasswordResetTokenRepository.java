package com.repository;

import com.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    public PasswordResetToken findByToken(String token);
    public void deleteAllByEmpId(int id);
}
