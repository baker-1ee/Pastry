package com.baker1ee.pastry.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final ZoneId KST_ZONE = ZoneId.of("Asia/Seoul");

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String dateText = jsonParser.getText();
        OffsetDateTime utcOffsetDateTime = OffsetDateTime.parse(dateText, DateTimeFormatter.ISO_DATE_TIME);

        ZonedDateTime kstDateTime = utcOffsetDateTime.atZoneSameInstant(KST_ZONE);
        return kstDateTime.toLocalDateTime();
    }
}
