package com.tunabel.perfumestorev1.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
public class BrandDTO {
    private int id;
    private String name;

    private String description;

    private String imageURL;
    private Date createdDate;

}
