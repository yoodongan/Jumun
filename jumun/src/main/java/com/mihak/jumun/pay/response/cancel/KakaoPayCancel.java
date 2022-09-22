package com.mihak.jumun.pay.response.cancel;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayCancel {
    private String aid, tid, cid;
    private String status;
    private String partner_order_id, partner_user_id, payment_method_type;
    private Amount amount;
    private ApprovedCancelAmount approved_cancel_amount;
    private CanceledAmount canceled_amount;
    private CancelAvailableAmount cancel_available_amount;
    private String item_name, item_code;
    private Integer quantity;
    private Date created_at, approved_at, canceled_at;
    private String payload;
}
