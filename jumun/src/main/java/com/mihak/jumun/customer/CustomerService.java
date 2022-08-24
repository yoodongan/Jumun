package com.mihak.jumun.customer;

/*엔티티 클래스는 컨트롤러에서 사용할수 없게끔 설계하는 것이 좋다. */
import com.mihak.jumun.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /*중복검사+생성까지 여기 서비스에서 처리.*/
    public Customer create(String nickname) {
        Customer customer = new Customer();
        customer.setNickname(nickname);
        customer.setVisitedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }
}
