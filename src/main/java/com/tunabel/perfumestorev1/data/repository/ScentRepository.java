package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Scent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScentRepository extends JpaRepository<Scent,Integer> {

    @Query("select count(s.id) from Scent s")
    long getTotalScents();

}
