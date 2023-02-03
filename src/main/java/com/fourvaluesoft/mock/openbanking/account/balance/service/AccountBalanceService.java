package com.fourvaluesoft.mock.openbanking.account.balance.service;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.exception.AccountBalanceNotFoundException;

public interface AccountBalanceService {

    AccountBalance getBalance(String tranDtime) throws AccountBalanceNotFoundException;
}
