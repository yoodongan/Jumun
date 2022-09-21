package com.mihak.jumun.storeMgmt.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindByUserDailyDto {
    private LocalDateTime orderedAt;
    private long userNickName;

    public LocalDateTime getOrderedAt(){
        return orderedAt;
    }
    public long getUserNickName(){
        return userNickName;
    }
    //Unable to locate appropriate constructor on class Expected arguments are: int, long
    //오류는 타입 재정의로 해결.
}
