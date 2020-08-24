package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSearchVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductSKURepositoryCustom {

    List<ProductSku> getNewArrivals(int limit);

    List<ProductSku> getBestSellers(int limit);

    Page<ProductSku> getPageByQuery(ProductSearchVM searchVM, PageRequest pageRequest, String sort);

    Page<ProductSku> getPageMainSkuByArrival(PageRequest pageRequest);




}
