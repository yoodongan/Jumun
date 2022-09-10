package com.mihak.jumun.customer.form;

import lombok.Data;

import java.util.List;

@Data
public class CustomerMenuForm {
    private String name;
    private boolean tnf;
    private List<String> options; // multi-checkbox
}