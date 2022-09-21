package com.mihak.jumun.order;

import com.mihak.jumun.entity.Order;
import com.mihak.jumun.storeMgmt.dto.FindByUserDailyDto;
import com.mihak.jumun.storeMgmt.dto.FindListFormDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 일반 SQL쿼리
    // 매출별 일간, 주간, 월간 쿼리
    @Query(value = "SELECT new com.mihak.jumun.storeMgmt.dto.FindListFormDto(o.orderedAt, sum(o.totalPrice))FROM Order o GROUP BY o.orderedAt")
    List<FindListFormDto> findByPriceDaily();
//    @Query(value = "SELECT DATE_FORMAT(DATE_SUB(`ordered_at`, INTERVAL (DAYOFWEEK(`ordered_at`)-1) DAY), '%Y/%m/%d') as start, DATE_FORMAT(DATE_SUB(`ordered_at`, INTERVAL (DAYOFWEEK(`ordered_at`)-7) DAY), '%Y/%m/%d') as end, DATE_FORMAT(`ordered_at`, '%Y%U') AS `date`, sum(`total_price`) FROM orders GROUP BY date", nativeQuery = true)
//    public List<Order> findByPriceWeekly();
//    @Query(value = "SELECT MONTH(`ordered_at`) AS `date`, sum(`total_price`) FROM orders GROUP BY `date`", nativeQuery = true)
//    public List<Order> findByPriceMonthly();

    // 이용자별 일간, 주간, 월간 쿼리
    @Query(value = "SELECT new com.mihak.jumun.storeMgmt.dto.FindByUserDailyDto(o.orderedAt, count(o.userNickName))FROM Order o GROUP BY o.orderedAt")
    List<FindByUserDailyDto> findByUserDaily();
//    @Query(value = "SELECT DATE_FORMAT(DATE_SUB(`ordered_at`, INTERVAL (DAYOFWEEK(`ordered_at`)-1) DAY), '%Y/%m/%d') as start, DATE_FORMAT(DATE_SUB(`ordered_at`, INTERVAL (DAYOFWEEK(`ordered_at`)-7) DAY), '%Y/%m/%d') as end, DATE_FORMAT(`ordered_at`, '%Y%U') AS `date`, sum(`user_nick_name`) FROM orders GROUP BY date", nativeQuery = true)
//    public List<Order> findByUserWeekly();
//    @Query(value = "SELECT MONTH(`ordered_at`) AS `date`, sum(`user_nick_name`) FROM orders GROUP BY `date`", nativeQuery = true)
//    public List<Order> findByUserMonthly();
}
