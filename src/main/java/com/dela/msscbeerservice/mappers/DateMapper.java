package com.dela.msscbeerservice.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {
    Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
        return (offsetDateTime != null) ?  convertOffsetDateTimeToTimestamp(offsetDateTime) : null;
    }

    OffsetDateTime asOffsetDateTime(Timestamp timestamp) {
        return (timestamp != null) ? convertTimestampToOffsetDateTime(timestamp) : null;
    }

    private Timestamp convertOffsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
    }

    private OffsetDateTime convertTimestampToOffsetDateTime(Timestamp timestamp) {
        return OffsetDateTime.of(timestamp.toLocalDateTime().getYear(),
                timestamp.toLocalDateTime().getMonthValue(),
                timestamp.toLocalDateTime().getDayOfMonth(),
                timestamp.toLocalDateTime().getHour(),
                timestamp.toLocalDateTime().getMinute(),
                timestamp.toLocalDateTime().getSecond(),
                timestamp.toLocalDateTime().getNano(),
                ZoneOffset.UTC);
    }
}
