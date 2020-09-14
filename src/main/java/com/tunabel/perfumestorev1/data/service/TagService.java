package com.tunabel.perfumestorev1.data.service;

import com.tunabel.perfumestorev1.data.model.blog.Tag;
import com.tunabel.perfumestorev1.data.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
