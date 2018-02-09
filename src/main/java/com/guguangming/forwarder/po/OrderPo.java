package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class OrderPo {
    @PrimaryKey
    private Integer id;
    private String billOfLadingNumber;
    private String entrustTheNumber;
    private String jobNumber;
    private String shipName;
    private String voyage;
    private String dockPlannerManifest;
    private String dateOfSailing;
    private String cutOfDate;
    private String shipCompanyName;
    private String boxAndBoxQuantity;
    private String portOfDischarge;
    private String placeOfDelivery;
    private String operatorName;
    private String operatorPhone;
    private String operatorEmail;
    private String documentaryClerkName;
    private String documentaryClerkPhone;
    private String documentaryClerkEmail;
}