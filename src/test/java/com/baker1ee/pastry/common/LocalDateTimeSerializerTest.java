package com.baker1ee.pastry.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocalDateTimeSerializerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test() throws JsonProcessingException {
        LocalDateTime kst = LocalDateTime.parse("2023-08-14T00:00:30");
        String actual = objectMapper.writeValueAsString(kst);
        assertThat(actual).isEqualTo("\"2023-08-13T15:00:30Z\"");
    }
}
