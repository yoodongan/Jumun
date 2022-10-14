package com.mihak.jumun.order;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
class OrderManagementControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String storeSN = "abcdefghijklmn";

    @Test
    public void showOrderList() throws Exception {

        mvc.perform(get("/%s/admin/store/order/list".formatted(storeSN)))
                .andExpect(status().isOk())
                .andExpect(view().name("orderList"))
                .andExpect(model().attributeExists("storeSN", "orderList"))
                .andExpect(model().attribute("storeSN", "abcdefghijklmn"))
                .andDo(print());
    }

    @Test
    void showOrderDetail() {
    }

    @Test
    void modify() {
    }
}