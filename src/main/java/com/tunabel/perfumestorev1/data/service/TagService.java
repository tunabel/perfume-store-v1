package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepository TagRepository;
}
