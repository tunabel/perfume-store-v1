package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional(readOnly = true)
    @Query("select u from User u where u.email = :email")
    Iterable<User> findByEmail(@Param("email") String email);

    @Transactional(readOnly = true)
    @Query("select u from User u where u.username = :username")
    Iterable<User> findByUsername(@Param("username") String username);

}
