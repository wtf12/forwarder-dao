package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class ImportantAnnouncementPo {
    @PrimaryKey
    private Integer id;
    private String announcementName;
    private String announcementContent;
    private String announcementTime;
}