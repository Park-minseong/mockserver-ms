package com.fourvaluesoft.mock.openbanking.account.balance.service.impl;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.exception.AccountBalanceNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.google.gson.Gson;

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
    public AccountBalance getBalance(String tranDtime) throws AccountBalanceNotFoundException {
        String dataFilePath = getDataFilePath(tranDtime);

        try {
            Gson gson = new Gson();

            InputStreamReader AccountBalanceReader = new InputStreamReader(new FileInputStream(dataFilePath), StandardCharsets.UTF_8);

            return gson.fromJson(AccountBalanceReader, AccountBalance.class);
        } catch (FileNotFoundException exception) {
            throw new AccountBalanceNotFoundException(dataFilePath + "를 찾지 못했습니다.");
        }
    }

    private String getDataFilePath(String tranDtime) {
        return rootPath + "/WEB-INF/resources/account_balance_" + tranDtime + ".json";
    }
}