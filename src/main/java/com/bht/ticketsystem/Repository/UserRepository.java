package com.bht.ticketsystem.Repository;

import com.bht.ticketsystem.entity.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
