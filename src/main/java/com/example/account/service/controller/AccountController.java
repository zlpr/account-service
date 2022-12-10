package com.example.account.service.controller;

import com.example.account.service.dto.Metric;
import com.example.account.service.entity.Account;
import com.example.account.service.repository.AccountRepository;
import com.example.account.service.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private AccountService accountService;
    private AccountRepository accountRepository;
    private MetricsEndpoint metricsEndpoint;

    @GetMapping("/{id}")
    public Long getAmount(@PathVariable Integer id) {
        return accountService.getAmount(id);
    }

    @PostMapping()
    public void addAmount(@RequestBody  Account account) {
        log.debug(account.toString());
        accountService.addAmount(account.getId(), account.getAmount());
    }
    @PutMapping()
    public Account create(@RequestParam Long amount){
        var ac = new Account();
        ac.setAmount(amount);
       return accountRepository.save(ac);
    }

    @GetMapping("/metric")
    public Metric getMetric() {
       var getAmount = metricsEndpoint.metric("getAmount",null).getMeasurements();
        var metric = new Metric(getAmount);
        return metric;
    }

}

