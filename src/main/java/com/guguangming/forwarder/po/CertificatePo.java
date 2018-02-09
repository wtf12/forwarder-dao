package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class CertificatePo {
    @PrimaryKey
    private Integer id;
    private String certificateName;
    private String certificateImgUrl;
    private String certificateSynopsis;
}