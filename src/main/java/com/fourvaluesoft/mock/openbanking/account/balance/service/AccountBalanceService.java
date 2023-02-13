package com.fourvaluesoft.mock.openbanking.account.balance.service;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;

public interface AccountBalanceService {

    AccountBalance getBalance(String tranDtime) throws DataNotFoundException;
}
