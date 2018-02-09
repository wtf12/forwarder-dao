package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class ProjectSuccessPo {
    @PrimaryKey
    private Integer id;
    private String projectName;
    private String projectImgUrl;
    private String projectSynopsis;
}