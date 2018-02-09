package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class BusinessScopePo {
    @PrimaryKey
    private Integer id;
    private String businessName;
    private String describeKeyword;
    private String businessContent;
}