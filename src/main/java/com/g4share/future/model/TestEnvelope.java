package com.g4share.future.model;

import java.util.List;

public class TestEnvelope {
    private final List<TestMessage> messages;

    public TestEnvelope(List<TestMessage> messages) {
        this.messages = messages;
    }

    public List<TestMessage> getMessages() {
        return messages;
    }
}