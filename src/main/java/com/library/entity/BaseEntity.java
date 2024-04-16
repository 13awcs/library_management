package com.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    protected Date createdTime;
    protected Date updatedTime;
    protected Long createdBy;
    protected Long updatedBy;

    @JsonIgnore
    protected boolean isDeleted = false;

}
