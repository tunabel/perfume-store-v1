package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.model.blog.Blog;
import com.tunabel.perfumestorev1.data.model.blog.Tag;
import com.tunabel.perfumestorev1.data.service.BlogService;
import com.tunabel.perfumestorev1.data.service.TagService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.BlogDto;
import com.tunabel.perfumestorev1.model.viewmodel.blog.TagVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/blogtag")
public class BlogTagApiController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    private String tagValidate(String tag) {
        return tag.trim().toLowerCase();
    }

    @PostMapping(value = "/newTag")
    public BaseApiResult createTag(@RequestParam String newTag) {
        DataApiResult result = new DataApiResult();

        String validatedTag = tagValidate(newTag);

        try {
            if (!tagService.isExistedName(validatedTag)) {

                Tag newTagEntity = new Tag();
                newTagEntity.setName(validatedTag);

                newTagEntity = tagService.save(newTagEntity);

                result.setData(newTagEntity);
                result.setMessage("Tag " + validatedTag + " added!");
                result.setSuccessful(true);
            } else {
                result.setSuccessful(false);
                result.setMessage("Tag " + validatedTag + " existed");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/update")
    public BaseApiResult updateBlog(@RequestBody BlogDto blogData) {
        DataApiResult result = new DataApiResult();

        if (blogData != null) {
            Blog blog = blogService.getById(blogData.getId());
            if (blog == null) {
                blog = new Blog();
            }

            blog.setTitle(blogData.getTitle());
            blog.setShortImg(blogData.getShortImg());
            blog.setShortDesc(blogData.getShortDesc());
            blog.setFullImg(blogData.getFullImg());
            blog.setFullDesc(blogData.getFullDesc());
            blog.setStatus(blogData.getStatus());
            blog.setCreatedDate(new Date());

            List<Tag> tagList = new ArrayList<>();
            for (int tagId : blogData.getTagIdList()) {
                Tag tag = tagService.getOne(tagId);
                if (tag != null) {
                    tagList.add(tag);
                }
            }

            blog.setTagList(tagList);

            try {
                blogService.save(blog);
                result.setMessage("Blog saved");
                result.setSuccessful(true);
            } catch (Exception e) {
                result.setSuccessful(false);
                result.setMessage(e.getMessage());
            }
        }
        return result;
    }

    @PostMapping(value = "/tag/save")
    public BaseApiResult saveTag(@RequestBody TagVM tag) {
        DataApiResult result = new DataApiResult();

        String validatedTagName = tagValidate(tag.getName());

        Tag possibleTag = tagService.getByName(validatedTagName);

        //if submitted tagname is existed, and submitted tag is new or has different ID
        if ((possibleTag != null && tag.getId() == null) ||
                (possibleTag != null && possibleTag.getId() != tag.getId())) {
            result.setSuccessful(false);
            result.setMessage("Tag " + validatedTagName + " existed");
            return result;
        }

        Tag savingTag = new Tag();

        try {
            //if the tag is to be updated:
            if ((possibleTag == null && tag.getId() != null) || (possibleTag != null && possibleTag.getId() == tag.getId())) {
                savingTag.setId(tag.getId());
            }

            savingTag.setName(validatedTagName);
            tagService.save(savingTag);

            result.setMessage("Tag " + validatedTagName + " added!");
            result.setSuccessful(true);

        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @DeleteMapping(value = "/tag/delete/{tagId}")
    public BaseApiResult deleteTag(@PathVariable int tagId) {
        DataApiResult result = new DataApiResult();

        try {
            boolean isTagDeleted = tagService.deleteOne(tagId);
            if (isTagDeleted) {
                result.setSuccessful(true);
                result.setMessage("Tag deleted successfully");
            } else {
                result.setSuccessful(false);
                result.setMessage("Can't delete tag");
            }
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
