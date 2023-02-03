package com.fourvaluesoft.mock.openbanking.account.balance.exception;

import java.io.IOException;

public class AccountBalanceNotFoundException extends IOException {

    public AccountBalanceNotFoundException() {
        super();
    }

    public AccountBalanceNotFoundException(String s) {
        super(s);
    }

    private AccountBalanceNotFoundException(String path, String reason) {
        super(path + ((reason == null)
                ? ""
                : " (" + reason + ")"));
    }
}
