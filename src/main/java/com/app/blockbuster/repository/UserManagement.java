package com.app.blockbuster.repository;

import com.app.blockbuster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserManagement extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);

    @Query("select u from User u where u.id in ?1")
    Optional<List<User>> getUsersById(Set<Long> users);

    Optional<User> findByEmailAndPassword(String user, String password);
}
