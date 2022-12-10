package com.example.account.service.service;

import com.example.account.service.entity.Account;
import com.example.account.service.repository.AccountRepository;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    private MetricService metricService;

    @Override
    @Timed("getAmount")
    public Long getAmount(Integer id) {
        metricService.increment("getAmount");
        return accountRepository.findById(id)
                .map(Account::getAmount)
                .orElse(0L);
    }

    @Override
    @Timed("addAmount")
    @Transactional
    public void addAmount(Integer id, Long value) {
        metricService.increment("addAmount");
        accountRepository.findById(id)
                .ifPresent((account)-> {
                    account.setAmount(account.getAmount() + value);
                    accountRepository.save(account);
                });
    }
}
