package com.mihak.jumun.store.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindByUserDailyDto {
    private LocalDateTime orderedAt;
    private long nickname;

    public FindByUserDailyDto(LocalDateTime orderedAt, long nickname) {
        this.orderedAt =  orderedAt;
        this.nickname = nickname;
    }
    public String calculateOrderedAtDaily(){
        // 날짜포맷에서 시간초를 제외
        String orderedAtDaily = orderedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return orderedAtDaily;
    }
    public long findByNickname(){
        return nickname;
    }
    //Unable to locate appropriate constructor on class Expected arguments are: int, long
    //오류는 타입 재정의로 해결.
}
