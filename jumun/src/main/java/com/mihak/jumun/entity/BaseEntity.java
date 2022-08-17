package com.mihak.jumun.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @CreatedBy
    private LocalDateTime createdDate;

    @LastModifiedBy
    private LocalDateTime lastModifiedDate;
}
