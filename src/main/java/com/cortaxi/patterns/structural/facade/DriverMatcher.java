package com.cortaxi.patterns.structural.facade;
import com.cortaxi.auth.Role;
import com.cortaxi.auth.Account;
import com.cortaxi.auth.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DriverMatcher {

    private final AccountRepository accountRepository;

    public DriverMatcher(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String findDriver(String pickup) {

        List<Account> drivers = accountRepository.findByRole(Role.DRIVER);

        if (drivers.isEmpty()) {
            return null;
        }

        Account driver = drivers.get(0);

        System.out.println("[DriverMatcher] selected driver: " + driver.getEmail());

        return "DRV-" + driver.getId();
    }
}