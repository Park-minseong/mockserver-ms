package com.fourvaluesoft.mock.openbanking.account.balance.service.impl;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AccountBalanceServiceImpl implements AccountBalanceService {

    @Override
    public AccountBalance getBalance(String tranDtime, String rootPath) {
        String dataPath = rootPath + "/WEB-INF/resources/account_balance_" + tranDtime + ".json";

        try {
            Gson gson = new Gson();

            InputStreamReader AccountBalanceReader = new InputStreamReader(new FileInputStream(dataPath), StandardCharsets.UTF_8);

            return gson.fromJson(AccountBalanceReader, AccountBalance.class);
        } catch (FileNotFoundException exception) {
            return new AccountBalance("A0021", "일치데이터 없음");
        }
    }
}