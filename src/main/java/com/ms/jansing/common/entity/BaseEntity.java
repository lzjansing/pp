package com.ms.jansing.common.entity;

import com.ms.jansing.common.util.IdGen;

/**
 * Created by jansing on 17-7-22.
 */
public class BaseEntity {
    private String id;
    private Boolean valid;

    public void preInsert() {
        this.setId(IdGen.uuid());
        this.setValid(Boolean.TRUE);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
