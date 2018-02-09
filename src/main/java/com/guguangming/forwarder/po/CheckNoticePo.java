package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class CheckNoticePo {
    @PrimaryKey
    private Integer id;
    private String notice;
}