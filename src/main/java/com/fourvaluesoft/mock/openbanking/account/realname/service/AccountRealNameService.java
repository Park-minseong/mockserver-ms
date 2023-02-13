package com.fourvaluesoft.mock.openbanking.account.realname.service;

import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;

public interface AccountRealNameService {

    AccountRealName getRealName(String accountNum) throws DataNotFoundException;
}
