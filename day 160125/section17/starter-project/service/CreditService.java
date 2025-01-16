package service;

import java.math.BigDecimal;

import pojo.Credit;
import repository.AccountRepository;

public class CreditService implements AccountService {
    AccountRepository accountRepository;

    public CreditService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void deposit(String id, BigDecimal amount) {
        Credit account = retrieveAccount(id);
        account.setCredit(account.getCredit().subtract(amount));
        updateAccount(account);
    }

    @Override
    public void withdraw(String id, BigDecimal amount) {
        Credit account = retrieveAccount(id);
        account.setCredit(account.getCredit().add(amount));
    }

    public void createAccount(Credit account) {

        this.accountRepository.createAccount(account);

    }

    public Credit retrieveAccount(String id) {
        return (Credit) this.accountRepository.retrieveAccount(id);
    }

    public void updateAccount(Credit account) {
        this.accountRepository.updateAccount(account);

    }

    public void deleteAccount(String id) {
        this.accountRepository.deleteAccount(id);

    }

}
