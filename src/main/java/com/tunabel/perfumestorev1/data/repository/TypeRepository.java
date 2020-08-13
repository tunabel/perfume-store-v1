package com.tunabel.perfumestorev1.data.repository;

import com.tunabel.perfumestorev1.data.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type,Integer> {

    @Query("select count(t.id) from Type t")
    long getTotalTypes();

}
