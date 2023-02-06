package com.fourvaluesoft.mock.openbanking.account.balance.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalance {

    private String apiTranId;
    private Long apiTranDtm;
    private String rspCode;
    private String rspMessage;
    private String bankTranId;
    private Integer bankTranDate;
    private String bankCodeTran;
    private String bankRspCode;
    private String bankRspMessage;
    private String bankName;
    private String savingsBankName;
    private String fintechUseNum;
    private Integer balanceAmt;
    private Integer availableAmt;
    private String accountType;
    private String productName;
    private Integer accountIssueData;
    private Integer maturityDate;
    private Integer lastTranDate;
}
