package com.tunabel.perfumestorev1.model.api;

import lombok.Data;

@Data
public class BaseApiResult {

   private boolean isSuccessful;
   private int httpResponse;
   private String message;
}
