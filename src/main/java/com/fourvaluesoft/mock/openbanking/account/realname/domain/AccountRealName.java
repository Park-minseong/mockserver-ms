package com.fourvaluesoft.mock.openbanking.account.realname.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRealName {

    private String apiTranId;
    private Long apiTranDtm;

    private String rspCode;
    private String rspMessage;

    private String bankTranId;
    private Integer bankTranDate;
    private String bankCodeTran;
    private String bankRspCode;
    private String bankRspMessage;
    private String bankCodeStd;
    private String bankCodeSub;
    private String bankName;
    private String savingsBankName;

    private String accountNum;
    private String accountSeq;
    private String accountHolderInfoType;
    private String accountHolderInfo;
    private String accountHolderName;
    private String accountType;
}
