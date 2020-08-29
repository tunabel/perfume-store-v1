package com.tunabel.perfumestorev1.model.api;

public class BaseApiResult {

   private boolean isSuccessful;
   private int httpResponse;
   private String message;

   public BaseApiResult() {
   }

   public boolean isSuccessful() {
      return this.isSuccessful;
   }

   public int getHttpResponse() {
      return this.httpResponse;
   }

   public String getMessage() {
      return this.message;
   }

   public void setSuccessful(boolean isSuccessful) {
      this.isSuccessful = isSuccessful;
   }

   public void setHttpResponse(int httpResponse) {
      this.httpResponse = httpResponse;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String toString() {
      return "BaseApiResult(isSuccessful=" + this.isSuccessful() + ", httpResponse=" + this.getHttpResponse() + ", message=" + this.getMessage() + ")";
   }
}
