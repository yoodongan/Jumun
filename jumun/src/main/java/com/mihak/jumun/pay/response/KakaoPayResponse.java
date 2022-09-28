package com.mihak.jumun.pay.response;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayResponse {

    private String tid;
    private String next_redirect_pc_url;
    private String next_redirect_mobile_url;
    private Date create_at;
}
