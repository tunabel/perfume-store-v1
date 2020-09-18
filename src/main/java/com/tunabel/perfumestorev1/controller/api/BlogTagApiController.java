package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.model.Brand;
import com.tunabel.perfumestorev1.data.model.blog.Tag;
import com.tunabel.perfumestorev1.data.service.BlogService;
import com.tunabel.perfumestorev1.data.service.TagService;
import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public BaseApiResult createBrand(@RequestParam String newTag) {
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
}
