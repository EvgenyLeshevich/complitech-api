package com.evgeniy.complitechapi.database.repository;

import com.evgeniy.complitechapi.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    @Query("select u from User u where (u.id between :f and :t)")
    List<User> selectUsersWithIdBetween(@Param("f") Long from, @Param("t") Long to);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from User u where (u.id between :f and :t)")
    void deleteUsersWithIdBetween(@Param("f") Long from, @Param("t") Long to);
}
