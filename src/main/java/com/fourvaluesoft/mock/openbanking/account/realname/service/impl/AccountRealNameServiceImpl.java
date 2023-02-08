package com.fourvaluesoft.mock.openbanking.account.realname.service.impl;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;
import com.fourvaluesoft.mock.openbanking.account.realname.service.AccountRealNameService;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AccountRealNameServiceImpl implements AccountRealNameService {

    private static final String RESOURCES_PATH = "/WEB-INF/resources/realName/";

    private String rootPath;

    public AccountRealNameServiceImpl(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public AccountRealName getRealName(String accountNum) throws AccountNotFoundException {
        String dataFilePath = getDataFilePath(accountNum);

        try {
            return loadFromFile(dataFilePath);
        } catch (IOException | JsonIOException | JsonSyntaxException ex) {
            throw createAccountNotFoundException(accountNum);
        }
    }

    private AccountRealName loadFromFile(String dataFilePath) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(Paths.get(dataFilePath)), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            return gson.fromJson(reader, AccountRealName.class);
        }
    }

    private String getDataFilePath(String accountNum) {
        return rootPath + RESOURCES_PATH + "account_real_name_" + accountNum + ".json";
    }

    private AccountNotFoundException createAccountNotFoundException(String accountNum) {
        return new AccountNotFoundException("Invalid accountNum: " + accountNum);
    }
}
