package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.CartSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartSkuRepository extends JpaRepository<CartSku,Integer> {

    @Query(value = "SELECT * FROM dbo_cart_sku cs " +
            "WHERE cs.cart_id = :cartId  " +
            "AND cs.sku_id = :skuId " +
            "ORDER BY cs.cart_sku_id DESC LIMIT 1",nativeQuery = true)
    CartSku findFirstCartSkuByCartIdAndSkuId(@Param("cartId") int cartId,
                                                     @Param("skuId") int skuId);

    @Query(value = "SELECT COUNT(cs.sku_id) FROM dbo_cart_sku cs " +
            "WHERE cs.cart_id = :cartId", nativeQuery = true)
    int countSkuByCartId(@Param("cartId") int cartId);

    @Query(value = "SELECT s FROM dbo_cart_sku cs " +
            "WHERE cs.cart_id = ("+
            "SELECT c.cart_id " +
            "FROM dbo_cart c " +
            "WHERE c.username = :username)",
            nativeQuery = true)
    public Long sumPendingOrderValueByUsername(@Param("username") String username);

}
