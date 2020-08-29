package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query(value = "SELECT * FROM dbo_cart c " +
            "WHERE :guid IS NULL OR c.guid = :guid " +
            "ORDER BY c.cart_id DESC LIMIT 1",nativeQuery = true)
    Cart findFirstCartByGuid(@Param("guid") String guid);

    @Query(value = "SELECT * from dbo_cart c " +
            "WHERE :username IS NULL OR c.username = :username " +
            "ORDER BY c.cart_id DESC LIMIT 1",nativeQuery = true)
    Cart findByUserName(@Param("username") String username);

}
