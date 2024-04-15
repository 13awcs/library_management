package com.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class BaseEntity {

    protected Date createdTime;
    protected Date updatedTime;
    protected Long createdBy;
    protected Long updatedBy;

    @JsonIgnore
    protected boolean isDeleted;

}
