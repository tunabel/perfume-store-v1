package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o " +
            "WHERE (:guid IS NULL OR (o.guid = :guid))" +
            "AND (:usernassme IS NULL OR (o.username = :username))")
    List<Order> findOrderByGuidOrUserName(@Param("guid") String guid, @Param("username") String username);



}
