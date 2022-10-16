/*리포지터리는 엔티티에 의해 생성된 데이터베이스 테이블에 접근하는 메서드들(예: findAll, save 등)을 사용하기 위한 인터페이스*/
package com.mihak.jumun.customer.repository;

import com.mihak.jumun.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/*닉네임을 조회*/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
