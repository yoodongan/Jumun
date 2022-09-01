package com.mihak.jumun;

import com.mihak.jumun.customer.CustomerRepository;
import com.mihak.jumun.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class JumunApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void choice_menu() {

	}
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void testJpa() {
		Customer c1 = new Customer();
		c1.setNickname("castlehyeon");
		c1.setVisitedAt(LocalDateTime.now());
		this.customerRepository.save(c1);  // 첫번째 질문 저장
	}

}
