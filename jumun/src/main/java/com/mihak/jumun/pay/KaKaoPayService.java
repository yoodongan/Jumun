package com.mihak.jumun.pay;

import com.mihak.jumun.entity.Order;
import com.mihak.jumun.order.OrderService;
import com.mihak.jumun.pay.response.KakaoPayApproval;
import com.mihak.jumun.pay.response.KakaoPayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class KaKaoPayService {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayResponse kakaoPayResponse;
    private KakaoPayApproval kakaoPayApproval;
    private final OrderService orderService;

    public String kakaoPayReady(Long orderId) {

        RestTemplate restTemplate = new RestTemplate();

        Order order = orderService.findOrderById(orderId);

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "4769b82c0c82ccdcfbaef41a7689ee28");
        headers.add("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", String.valueOf(order.getId()));
        params.add("partner_user_id", order.getUserNickName());
        params.add("item_name", order.getUserNickName() + "님의 주문하신 음식");
        params.add("quantity", "1");
        params.add("total_amount", String.valueOf(order.getTotalPrice()));
        params.add("tax_free_amount", String.valueOf(Math.round(order.getTotalPrice() * 0.9)));
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess/" + orderId);
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel" + orderId);
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail" + orderId);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayResponse = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayResponse.class);
            return kakaoPayResponse.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "/pay";
    }

    public KakaoPayApproval kakaoPayInfo(String pg_token, Long orderId) {

        RestTemplate restTemplate = new RestTemplate();

        Order order = orderService.findOrderById(orderId);

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "4769b82c0c82ccdcfbaef41a7689ee28");
        headers.add("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayResponse.getTid());
        params.add("partner_order_id", String.valueOf(order.getId()));
        params.add("partner_user_id", order.getUserNickName());
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(Math.round(order.getTotalPrice())));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayApproval = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApproval.class);
            return kakaoPayApproval;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
