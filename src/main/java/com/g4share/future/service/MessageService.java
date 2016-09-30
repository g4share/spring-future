package com.g4share.future.service;

import com.g4share.future.model.TestEnvelope;
import com.g4share.future.model.TestMessage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageService {
    private Logger logger = Logger.getLogger(MessageService.class);

    private Random random = new Random();

    private int randomTime(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private int randomSleep() {
        int sleepTime = randomTime(500, 520);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return sleepTime;
    }

    private TestMessage getMessage(String message) {
        Date startCreationDate = new Date();
        int sleepTime = randomSleep();
        return new TestMessage(message, startCreationDate, sleepTime);
    }

    public TestEnvelope m(int count) {
        List<TestMessage> testMessages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            testMessages.add(getMessage("m" + i));
        }
        return new TestEnvelope(testMessages);
    }

    public CompletableFuture<TestEnvelope> mf(int count) {
        return CompletableFuture.supplyAsync(() ->  m(count));
    }

    public CompletableFuture<TestEnvelope> m1(String msg) {
        return CompletableFuture.supplyAsync(() ->   {
            List<TestMessage> testMessages = new ArrayList<>();
            testMessages.add(getMessage(msg));
            return new TestEnvelope(testMessages);
        });
    }

    public TestEnvelope combine(TestEnvelope m1, TestEnvelope m2){
        List<TestMessage> combinedMessages = new ArrayList<>();
        combinedMessages.addAll(m1.getMessages());
        combinedMessages.addAll(m2.getMessages());
        return new TestEnvelope(combinedMessages);
    }
}