package com.guguangming.forwarder.po;

import lombok.Data;

import com.moxie.commons.annotation.*;
import java.util.*;

@Data
public class EmployeesPo {
    @PrimaryKey
    private Integer id;
    private String employeesImgUrl;
    private String employeesSynopsis;
    private String employeesImgName;
}