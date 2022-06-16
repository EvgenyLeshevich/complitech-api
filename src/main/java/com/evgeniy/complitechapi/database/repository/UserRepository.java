package com.evgeniy.complitechapi.database.repository;

import com.evgeniy.complitechapi.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
