package com.fourvaluesoft.mock.openbanking.account.balance.service.impl;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;


public class AccountBalanceServiceImpl implements AccountBalanceService {
    @Override
    public AccountBalance getBalance(String fintechUseNum, String bankTranId, String tranDtime) {
        return new AccountBalance();
    }
}
