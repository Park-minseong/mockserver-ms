package com.fourvaluesoft.mock.openbanking.account.balance.service;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;

public interface AccountBalanceService {

    AccountBalance getBalance(String fintechUseNum, String bankTranId, String tranDtime);

    AccountBalance getBalance(String tranDtime);
}
