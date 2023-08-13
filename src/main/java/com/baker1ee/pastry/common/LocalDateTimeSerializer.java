package com.baker1ee.pastry.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        OffsetDateTime kstOffsetDateTime = value.atZone(ZoneId.of("Asia/Seoul")).toOffsetDateTime();
        OffsetDateTime utcOffsetDateTime = kstOffsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formattedDateTime = formatter.format(utcOffsetDateTime);
        gen.writeString(formattedDateTime);
    }
}
