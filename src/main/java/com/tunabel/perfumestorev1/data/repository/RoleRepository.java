package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r " +
            "JOIN User u " +
            "WHERE u.id = :userId")
    List<Role> findAllByUserId(int userId);
}
