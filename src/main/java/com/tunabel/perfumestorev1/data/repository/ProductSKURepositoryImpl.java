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
