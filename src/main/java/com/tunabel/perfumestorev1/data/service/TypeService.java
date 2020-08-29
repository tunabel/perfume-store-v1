package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.Type;
import com.tunabel.perfumestorev1.data.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type findOne(int typeId) {
        return typeRepository.findOne(typeId);
    }

    public List<Type> getTypeList() {
        try {
            return typeRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public long getTotalTypes() {
        return typeRepository.getTotalTypes();
    }

}
