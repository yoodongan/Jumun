package com.mihak.jumun.order.dto;

import com.mihak.jumun.entity.PayType;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFormDto {
    private String requests;
    private PayType payType;
}
