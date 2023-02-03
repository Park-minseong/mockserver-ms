package com.fourvaluesoft.mock.openbanking.account.balance.service;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;

import java.io.FileNotFoundException;

public interface AccountBalanceService {

    AccountBalance getBalance(String tranDtime, String rootPath) throws FileNotFoundException;
}
