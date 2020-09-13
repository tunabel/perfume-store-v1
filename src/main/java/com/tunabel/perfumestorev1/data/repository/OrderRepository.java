package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Order;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o " +
            "WHERE (:guid IS NULL OR (o.guid = :guid))" +
            "AND (:username IS NULL OR (o.username = :username))")
    List<Order> findOrderByGuidOrUserName(@Param("guid") String guid, @Param("username") String username);


    @Query("SELECT o FROM Order o")
    Page<Order> getPage(Pageable pageable);

    @Query("SELECT o FROM Order o " +
            "WHERE o.username = :username")
    Page<Order> getPageByUsername(Pageable pageable, @Param("username") String username);
}
