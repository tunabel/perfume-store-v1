package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Order;
import com.tunabel.perfumestorev1.data.model.ProductSku;
import com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM;
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

    @Query("SELECT new com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM(MONTH(o.createdDate), SUM(o.totalPrice)) FROM Order o " +
            "WHERE YEAR(o.createdDate) = :year " +
            "GROUP BY MONTH(o.createdDate) " +
            "ORDER BY MONTH(o.createdDate)")
    List<ChartDataVM> getRevenueMonthByYear(@Param("year") Integer year);


    @Query("SELECT new com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM(DAY(o.createdDate), SUM(o.totalPrice)) FROM Order o " +
            "WHERE MONTH(o.createdDate) = :month " +
            "AND YEAR(o.createdDate) = :year " +
            "GROUP BY DAY(o.createdDate) " +
            "ORDER BY DAY(o.createdDate)")
    List<ChartDataVM> getRevenueDayByMonth(@Param("month") Integer month, @Param("year") Integer year);
}
