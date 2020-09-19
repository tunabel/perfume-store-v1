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

    public List<Tag> getAllExceptForBlogId(int blogId) {
        return tagRepository.findAllExceptForBlogId(blogId);
    }

    public boolean isExistedName(String tagName) {
        List<Tag> tagList = tagRepository.findByName(tagName);
        return (tagList.size() > 0) ? true : false;
    }

    public Tag save(Tag newTagEntity) {
        return tagRepository.save(newTagEntity);
    }

    public Tag getOne(int tagId) {
        return tagRepository.findOne(tagId);
    }

    public Tag getByName(String name) {
        List<Tag> results =  tagRepository.findByName(name);

        if (results.size() > 0 ) {
            return results.get(0);
        }
        return null;
    }

    public boolean deleteOne(int tagId) {
        Tag tag = tagRepository.findOne(tagId);
        if (tag == null) {
            return false;
        } else {
            tagRepository.delete(tagId);
            return true;
        }

    }
}
