package com.example.account.service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
@Service
@AllArgsConstructor
public class MetricService {

    private List<Counter> counters;

    @Scheduled(fixedDelay = 10_000)
    public void printMetrics() {
        counters.forEach(counter -> {
          var v = counter.getValue().get();
          var ov = counter.getOldValue().get();
          counter.getOldValue().set(v);
          log.info("{}: Count: {} Count time10s: {}",counter.name,v,v-ov);
        });
    }

    public void createCounter(String name){
        counters.add(new Counter(name,new AtomicInteger(),new AtomicInteger()));
    }

    public void increment(String name) {
        counters.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .ifPresent(Counter::increment);
    }

    @Data
    @AllArgsConstructor
    public static class Counter{
        private String name;
        private AtomicInteger value;
        private AtomicInteger oldValue;

        public void increment(){
            value.incrementAndGet();
        }
    }
}
