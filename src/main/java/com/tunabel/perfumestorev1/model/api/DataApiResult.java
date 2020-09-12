package com.tunabel.perfumestorev1.model.api;

import lombok.Getter;
import lombok.Setter;

public class DataApiResult extends BaseApiResult {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
