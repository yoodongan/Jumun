package com.mihak.jumun.pay.response.cancel;

import lombok.Data;

@Data
public class CanceledAmount {
    private Integer total;
    private Integer tax_free;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer green_deposit;
}
