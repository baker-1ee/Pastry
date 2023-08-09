package com.baker1ee.pastry.id;

class DummyIdGenerator implements IdGenerator {
    @Override
    public long nextId() {
        throw new IllegalStateException("IdGenerator 가 초기화 되지 않았습니다.");
    }
}
