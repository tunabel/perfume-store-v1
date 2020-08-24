package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSearchVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductSKURepositoryImpl implements ProductSKURepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductSku> getNewArrivals(int limit) {
        return entityManager.createQuery("SELECT p FROM ProductSku p ORDER BY p.createdDate DESC",
                ProductSku.class).setMaxResults(limit).getResultList();
    }

    @Override
    public List<ProductSku> getBestSellers(int limit) {
        Query query = entityManager.createNativeQuery("SELECT p.*, SUM(op.quantity)AS SALES FROM dbo_product_sku p\n" +
                "JOIN dbo_order_sku op\n" +
                "ON op.sku_id = p.sku_id\n" +
                "GROUP BY p.sku_id\n" +
                "ORDER BY SALES DESC\n" +
                "LIMIT ?");
        query.setParameter(1,limit);
        return query.getResultList();
    }

    @Override
    public Page<ProductSku> getPageByQuery(ProductSearchVM searchVM, PageRequest pageRequest, String sort) {
        return null;
    }


    @Override
    public Page<ProductSku> getPageMainSkuByArrival(PageRequest pageRequest) {
        return null;
    }


    private String convertSortString(String sort) {
        String result;
        switch(sort) {
            case "best" :
                result = "SALES DESC";
                break;
            case "new" :
                result = "s.createdDate DESC";
                break;
            case "priceAsc" :
                result = "s.price ASC";
                break;
            case "priceDesc" :
                result = "s.price DESC";
                break;
            default:
                result = "s.id ASC";
                break;
        }
        return  result;
    }
}
