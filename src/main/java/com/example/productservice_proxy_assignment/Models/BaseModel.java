package com.example.productservice_proxy_assignment.Models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public abstract class BaseModel {
    private Long id;
    private Date CreatedDate;
    private Date LastModifiedDate;
    private boolean isDeleted;
}
