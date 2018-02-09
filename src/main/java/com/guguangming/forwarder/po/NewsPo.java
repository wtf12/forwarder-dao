package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class NewsPo {
    @PrimaryKey
    private Integer id;
    private String newsTitle;
    private String newsImgUrl;
    private String newsContent;
    private String newsSynopsis;
    private String newsSynopsisImgUrl;
    private String newsTime;
}