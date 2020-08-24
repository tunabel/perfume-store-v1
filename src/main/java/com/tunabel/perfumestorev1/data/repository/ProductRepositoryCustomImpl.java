package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    EntityManager entityManager;

//    @Override
//    public Product findBySkuId(int skuId) {
//
//        Query query = entityManager.createNativeQuery(
//                "SELECT p.* FROM dbo_product p " +
//                "JOIN dbo_product_sku s " +
//                "ON s.product_id = s.product_id " +
//                "WHERE s.sku_id = ? ");
//        query.setParameter(1,skuId);
//        int product = query.getFirstResult();
//        List<Object> list = query.getResultList();
//
//        return (Product) query.getResultList().get(0);
//    }
}
