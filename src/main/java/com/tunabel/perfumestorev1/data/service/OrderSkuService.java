package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.repository.OrderSkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class OrderSkuService {
    @Autowired
    OrderSkuRepository orderSkuRepository;


}
