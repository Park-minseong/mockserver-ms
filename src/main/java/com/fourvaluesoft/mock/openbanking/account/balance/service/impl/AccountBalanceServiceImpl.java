package com.fourvaluesoft.mock.openbanking.account.balance.service.impl;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AccountBalanceServiceImpl implements AccountBalanceService {

    private String rootPath;

    public AccountBalanceServiceImpl(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public AccountBalance getBalance(String tranDtime) throws AccountNotFoundException {
        String dataFilePath = getDataFilePath(tranDtime);

        try {
            return loadAccountBalanceFromFile(dataFilePath);
        } catch (JsonIOException ex) {
            throw createAccountNotFoundException(tranDtime);
        } catch (JsonSyntaxException ex) {
            throw createAccountNotFoundException(tranDtime);
        } catch (FileNotFoundException ex) {
            throw createAccountNotFoundException(tranDtime);
        }
    }

    private AccountBalance loadAccountBalanceFromFile(String dataFilePath) throws FileNotFoundException {
        Gson gson = new Gson();

        InputStreamReader reader = new InputStreamReader(new FileInputStream(dataFilePath), StandardCharsets.UTF_8);

        return gson.fromJson(reader, AccountBalance.class);
    }

    private String getDataFilePath(String tranDtime) {
        return rootPath + "/WEB-INF/balance/resources/account_balance_" + tranDtime + ".json";
    }

    private AccountNotFoundException createAccountNotFoundException(String tranDtime) {
        return new AccountNotFoundException("Invalid tranDtime: " + tranDtime);
    }
}