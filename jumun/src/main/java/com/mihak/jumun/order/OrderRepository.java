package com.mihak.jumun.order;

import com.mihak.jumun.entity.Order;
import com.mihak.jumun.storeMgmt.dto.FindByUserDailyDto;
import com.mihak.jumun.storeMgmt.dto.FindListFormDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 매출별 일간쿼리. JPQL에서 지원하지 않는 date함수
    @Query(value = "SELECT new com.mihak.jumun.storeMgmt.dto.FindListFormDto(o.orderedAt, sum(o.totalPrice))FROM Order o WHERE o.storeSerialNumber = :storeSN GROUP BY o.orderedAt")
    List<FindListFormDto> findByPriceDaily(@Param("storeSN") String storeSN);
    // 이용자별 일간쿼리
    @Query(value = "SELECT new com.mihak.jumun.storeMgmt.dto.FindByUserDailyDto(o.orderedAt, count(o.userNickName))FROM Order o WHERE o.storeSerialNumber = :storeSN GROUP BY o.orderedAt")
    List<FindByUserDailyDto> findByUserDaily(@Param("storeSN") String storeSN);
    Optional<Order> findByUserNickName(String userNickName);
}
