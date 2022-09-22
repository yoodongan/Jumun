package com.mihak.jumun.storeMgmt.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindByUserDailyDto {
    private LocalDateTime orderedAt;
    private long userNickName;

    public FindByUserDailyDto(LocalDateTime orderedAt, long userNickName) {
        this.orderedAt =  orderedAt;
        this.userNickName = userNickName;
    }
    public String getChangeOrderedAt(){
        // 날짜포맷에서 시간초를 제외
        String orderedAtDaily = orderedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return orderedAtDaily;
    }
    public long getUserNickName(){
        return userNickName;
    }
    //Unable to locate appropriate constructor on class Expected arguments are: int, long
    //오류는 타입 재정의로 해결.
}
