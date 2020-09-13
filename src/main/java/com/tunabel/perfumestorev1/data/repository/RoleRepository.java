package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT * FROM dbo_role r " +
            "LEFT JOIN dbo_user_role ur " +
            "ON r.role_id = ur.role_id " +
            "WHERE ur.user_id = :userId ", nativeQuery = true)
    List<Role> findAllByUserId(@Param("userId") int userId);
}
