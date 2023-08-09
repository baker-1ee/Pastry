package com.baker1ee.pastry.id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdHolder {

    private static IdGenerator _idGenerator = new DummyIdGenerator();

    // Lazy init
    static void init(IdGenerator idGenerator) {
        _idGenerator = idGenerator;
        log.info("IdHolder 초기화 성공 {}", nextId());
    }

    public static long nextId() {
        return _idGenerator.nextId();
    }

}
