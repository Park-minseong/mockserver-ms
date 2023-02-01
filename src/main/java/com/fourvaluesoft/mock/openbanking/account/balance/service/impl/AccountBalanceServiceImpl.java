package com.fourvaluesoft.mock.openbanking.account.balance.service.impl;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;

public class AccountBalanceServiceImpl implements AccountBalanceService {

    @Override
    public AccountBalance getBalance(String fintechUseNum, String bankTranId, String tranDtime) {
     return new AccountBalance(1000000, "내맘대로통장", 20191010);
    }

    @Override
    public AccountBalance getBalance(String tranDtime) {
        AccountBalance accountBalance = new AccountBalance();

        if (tranDtime.equals("202202020202")) {
            accountBalance.setProductName("내맘대로통장");
            accountBalance.setBalanceAmt(100000000);
            accountBalance.setLastTranDate(20220202);
        } else if (tranDtime.equals("202301010101")) {
            accountBalance.setProductName("네맘대로통장");
            accountBalance.setBalanceAmt(500);
            accountBalance.setLastTranDate(20221231);
        }

        return accountBalance;
    }
}
