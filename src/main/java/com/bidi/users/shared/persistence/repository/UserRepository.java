package com.bidi.users.shared.persistence.repository;

import com.bidi.users.shared.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
