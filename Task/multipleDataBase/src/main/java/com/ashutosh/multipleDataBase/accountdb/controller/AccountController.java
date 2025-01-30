package com.ashutosh.multipleDataBase.accountdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.multipleDataBase.accountdb.entity.Account;
import com.ashutosh.multipleDataBase.accountdb.repository.AccountRepository;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @PostMapping("api/v1/accounts")
    public Account saveAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }
    @GetMapping ("api/v1/accounts")
    public List<Account> getAccountList(){
        return accountRepository.findAll();

    }
}
