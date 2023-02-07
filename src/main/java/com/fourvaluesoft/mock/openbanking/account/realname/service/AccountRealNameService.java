package com.fourvaluesoft.mock.openbanking.account.realname.service;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;

public interface AccountRealNameService {

    AccountRealName getRealName(String accountNumJson) throws AccountNotFoundException;
}
