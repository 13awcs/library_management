package com.library.auth.repository;

import com.library.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select u.id, u.username, u.password, u.employee_id, u.role from user u where u.username = ?1 and u.password = ?2", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);
}
