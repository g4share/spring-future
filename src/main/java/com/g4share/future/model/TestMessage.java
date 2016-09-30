package com.g4share.future.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class TestMessage {
    private final String message;

    @JsonFormat(pattern = "mmssSSS")
    private final Date createdDate;

    @JsonFormat(pattern = "mmssSSS")
    private final Date startCreationDate;

    private final String threadName;
    private final int sleepTime;

    public TestMessage(String message, Date startCreationDate, int sleepTime) {
        this.message = message;
        this.sleepTime = sleepTime;
        this.startCreationDate = startCreationDate;

        createdDate = new Date();
        threadName = Thread.currentThread().getName();
    }

    public String getMessage() {
        return message;
    }

    public Date getStartCreationDate() {
        return startCreationDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getThreadName() {
        return threadName;
    }

    public int getSleepTime() {
        return sleepTime;
    }
}