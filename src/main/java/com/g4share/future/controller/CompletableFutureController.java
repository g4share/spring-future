package com.g4share.future.controller;

import com.g4share.future.model.TestEnvelope;
import com.g4share.future.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class CompletableFutureController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    @RequestMapping("/m{n}/synch")
    @ResponseStatus(HttpStatus.OK)
    public TestEnvelope mSync(@PathVariable int n) {
        return messageService.m(n);
    }

    @GetMapping
    @RequestMapping("/m{n}/future")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<TestEnvelope> mFuture(@PathVariable int n) {
        return messageService.mf(n);
    }

    @GetMapping
    @RequestMapping("/m{n}/combine")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<TestEnvelope> mCombine(@PathVariable int n) throws ExecutionException, InterruptedException {
        List<CompletableFuture<TestEnvelope>> futures = generateEnvelopes(n);

        CompletableFuture<TestEnvelope> fp = futures.get(0);

        for (int i = 1; i < n; i++) {
            fp = fp.thenCombine(futures.get(i), (fpr, mNr) -> messageService.combine(fpr, mNr));
        }

        return fp;
    }

    @GetMapping
    @RequestMapping("/m{n}/stream")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<TestEnvelope> mStream(@PathVariable int n) throws ExecutionException, InterruptedException {

        List<CompletableFuture<TestEnvelope>> futures = generateEnvelopes(n);

        Optional<CompletableFuture<TestEnvelope>> ret = futures
                .stream()
                .reduce((f1, f2) -> f1.thenCombine(f2, (l1, l2) ->
                        messageService.combine(l1, l2)
                ));

        return ret.get();
    }

    @GetMapping
    @RequestMapping("/m{n}/parallelstream")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<TestEnvelope> mParallelStream(@PathVariable int n) throws ExecutionException, InterruptedException {

        List<CompletableFuture<TestEnvelope>> futures = generateEnvelopes(n);

        Optional<CompletableFuture<TestEnvelope>> ret = futures
                .parallelStream()
                .reduce((f1, f2) -> f1.thenCombineAsync(f2, (l1, l2) ->
                        messageService.combine(l1, l2)
                ));

        return ret.get();
    }

    private List<CompletableFuture<TestEnvelope>> generateEnvelopes(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> messageService.m1("m" + i))
                .collect(Collectors.toList());
    }
}