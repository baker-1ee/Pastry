package com.baker1ee.pastry.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
class SnowflakeIdGenerator implements IdGenerator {
    private static final long START_TIMESTAMP = 1672498800000L; // 2022-12-31 15:00:00 UTC

    private static final int SERVER_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    private static final long MAX_SERVER_ID = (1L << SERVER_ID_BITS) - 1; // 1023 (2^10 - 1)
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1; // 4095 (2^12 - 1)

    private final long serverId;

    private long lastTimestamp = 0L;
    private long sequence = 0L;

    @Autowired
    SnowflakeIdGenerator() {
        // 서버 별로 고유한 serverId 를 보장할 수만 있으면 어떤 값이 와도 상관 없다.
        long serverId = 1000L;
        if (serverId < 0 || serverId > MAX_SERVER_ID) {
            throw new IllegalArgumentException(String.format("Server ID must be between %d and %d", 0, MAX_SERVER_ID));
        }
        this.serverId = serverId;

        IdHolder.init(this);
    }

    /**
     * Create next unique id
     *
     * @return long type id
     */
    @Override
    public synchronized long nextId() {
        long currentTimestamp = Instant.now().toEpochMilli();
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }
        if (lastTimestamp == currentTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE; // roll over
            if (sequence == 0) {
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = currentTimestamp;

        // make 64bit Unique ID
        return ((currentTimestamp - START_TIMESTAMP) << (SERVER_ID_BITS + SEQUENCE_BITS)) |
                (serverId << SEQUENCE_BITS) |
                sequence;
    }

    // wait a millisecond
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = Instant.now().toEpochMilli();
        while (timestamp <= lastTimestamp) {
            timestamp = Instant.now().toEpochMilli();
        }
        return timestamp;
    }

    /**
     * disassemble created datetime from id
     *
     * @param id id
     * @return id created datetime
     */
    public static LocalDateTime getCreatedLocalDateTime(long id) {
        long timestamp = id >> (SERVER_ID_BITS + SEQUENCE_BITS);
        long createdTimestamp = timestamp + START_TIMESTAMP;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(createdTimestamp), ZoneId.systemDefault());
    }

    /**
     * disassemble serverId from id
     *
     * @param id id
     * @return serverId that created the id
     */
    public static long getServerId(long id) {
        return (id >> SEQUENCE_BITS) & MAX_SERVER_ID;
    }

    /**
     * disassemble sequence from id
     *
     * @param id id
     * @return sequence of id
     */
    public static long getSequence(long id) {
        return id & MAX_SEQUENCE;
    }
}
