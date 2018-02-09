package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class SailingSchedulePo {
    @PrimaryKey
    private Integer id;
    private String scheduleName;
    private String scheduleSize;
    private String scheduleType;
    private String scheduleUrl;
}