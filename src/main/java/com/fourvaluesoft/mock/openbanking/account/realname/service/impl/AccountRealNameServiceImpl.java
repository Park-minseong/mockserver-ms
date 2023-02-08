package com.fourvaluesoft.mock.openbanking.account.realname.service.impl;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;
import com.fourvaluesoft.mock.openbanking.account.realname.service.AccountRealNameService;
import com.google.gson.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AccountRealNameServiceImpl implements AccountRealNameService {

    private String rootPath;
    private final String RESOURCES_PATH = "/WEB-INF/resources/realName/";

    public AccountRealNameServiceImpl(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public AccountRealName getRealName(String accountNum) throws AccountNotFoundException {
        String dataFilePath = getDataFilePath(accountNum);

        try {
            return loadAccountRealNameFromFile(dataFilePath);
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException ex) {
            throw createAccountNotFoundException(accountNum);
        }
    }

    private AccountRealName loadAccountRealNameFromFile(String dataFilePath) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        InputStreamReader reader = new InputStreamReader(new FileInputStream(dataFilePath), StandardCharsets.UTF_8);

        return gson.fromJson(reader, AccountRealName.class);
    }

    private String getDataFilePath(String accountNum) {
        return rootPath + RESOURCES_PATH + "account_real_name_" + accountNum + ".json";
    }

    private AccountNotFoundException createAccountNotFoundException(String accountNum) {
        return new AccountNotFoundException("Invalid accountNum: " + accountNum);
    }
}
