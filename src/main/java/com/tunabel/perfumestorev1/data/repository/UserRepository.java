package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT u FROM User u " +
            "WHERE (:name IS NULL OR UPPER(u.username) LIKE CONCAT('%',UPPER(:name),'%') OR UPPER(u.name) LIKE CONCAT('%',UPPER(:name),'%'))")
    Page<User> getUserListByNameContaining(Pageable pageable, @Param("name") String name);
}
