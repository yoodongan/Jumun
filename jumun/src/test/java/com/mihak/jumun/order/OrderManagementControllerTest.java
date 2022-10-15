package com.mihak.jumun.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class OrderManagementControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String storeSN = "abcdefghijklmn";
    private final String orderId = "1";


    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    @DisplayName(value = "주문현황_보여주기")
    public void showOrderList() throws Exception {

        mvc.perform(get("/%s/admin/store/order/list".formatted(storeSN)))
                .andExpect(status().isOk())
                .andExpect(view().name("order/orderList"))
                .andExpect(model().attributeExists("storeSN", "orderList"))
                .andExpect(model().attribute("storeSN", "abcdefghijklmn"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    @DisplayName(value = "주문상세현황_보여주기")
    void showOrderDetail() throws Exception {

        mvc.perform(get("/%s/admin/store/order/detail/%s".formatted(storeSN, orderId)))
                .andExpect(status().isOk())
                .andExpect(view().name("order/order_detail"))
                .andExpect(model().attribute("cartListDto", "order"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    @DisplayName(value = "주문현황_변경")
    void modify() throws Exception {

        mvc.perform(post("/%s/admin/store/order/modify/%s".formatted(storeSN, orderId)))
                .andExpect(redirectedUrl("/%s/admin/store/order/list".formatted(storeSN)));
    }
}