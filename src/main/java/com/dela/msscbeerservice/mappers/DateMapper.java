package com.dela.msscbeerservice.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DateMapper {
    Timestamp asTimestamp(LocalDateTime localDateTime) {
        return (localDateTime != null) ?  convertLocalDateTimeToTimestamp(localDateTime) : null;
    }

    LocalDateTime asLocalDateTime(Timestamp timestamp) {
        return (timestamp != null) ? convertTimestampToLocalDateTime(timestamp) : null;
    }

    private Timestamp convertLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime convertTimestampToLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.of(timestamp.toLocalDateTime().getYear(),
                timestamp.toLocalDateTime().getMonthValue(),
                timestamp.toLocalDateTime().getDayOfMonth(),
                timestamp.toLocalDateTime().getHour(),
                timestamp.toLocalDateTime().getMinute(),
                timestamp.toLocalDateTime().getSecond());
    }
}
