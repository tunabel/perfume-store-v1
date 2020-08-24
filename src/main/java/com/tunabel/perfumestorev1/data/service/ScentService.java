package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Scent;
import com.tunabel.perfumestorev1.data.repository.ScentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScentService {

    @Autowired
    private ScentRepository scentRepository;

    public Scent findOne(int scentId) {
        return scentRepository.findById(scentId).get();
    }

    public List<Scent> getScentList() {
        try {
            return scentRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public long getTotalScents() {
        return scentRepository.getTotalScents();
    }

}
