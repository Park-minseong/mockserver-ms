package com.fourvaluesoft.mock.openbanking.account.balance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalance {

    private String apiTranId;
    private long apiTranDtm;
    private String rspCode;
    private String rspMessage;
    private String bankTranId;
    private int bankTranDate;
    private String bankCodeTran;
    private String bankRspCode;
    private String bankRspMessage;
    private String bankName;
    private String savingsBankName;
    private String fintechUseNum;
    private int balanceAmt;
    private int availableAmt;
    private String accountType;
    private String productName;
    private int accountIssueData;
    private int maturityDate;
    private int lastTranDate;

    public AccountBalance(int balanceAmt, String productName, int lastTranDate) {
        this.balanceAmt = balanceAmt;
        this.productName = productName;
        this.lastTranDate = lastTranDate;
    }
}
