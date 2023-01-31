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

    private String apiTranId = "AA12349BHZ1324K82AL3";
    private String apiTranDtm = "20160310101921567";
    private String rspCode = "A0000";
    private String rspMessage = "";
    private String bankTranId = "12345678901234567890";
    private String bankTranDate = "20160310";
    private String bankCodeTran = "098";
    private String bankRspCode = "000";
    private String bankRspMessage =	"";
    private String bankName = "오픈뱅킹";
    private String savingsBankName = "오픈저축은행";
    private String fintechUseNum = "123456789012345678901234";
    private String balanceAmt = "1000000";
    private String availableAmt = "1000000";
    private String accountType = "1";
    private String productName = "내맘대로통장";
    private String accountIssueData = "20190110";
    private String maturityDate = "20200109";
    private String lastTranDate = "20191010";

}
