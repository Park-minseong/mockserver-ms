package com.fourvaluesoft.mock.openbanking.account.balance.service.impl;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AccountBalanceServiceImpl implements AccountBalanceService {

    private static final String DATA_PATH = "/WEB-INF/data/account/balance/";

    private final String webResourcesPath;

    public AccountBalanceServiceImpl(String webResourcesPath) {
        this.webResourcesPath = webResourcesPath;
    }

    @Override
    public AccountBalance getBalance(String tranDtime) throws DataNotFoundException {
        String dataFilePath = getDataFilePath(tranDtime);

        try {
            return loadFromFile(dataFilePath);
        } catch (IOException | JsonIOException | JsonSyntaxException ex) {
            throw createDataNotFoundException(tranDtime);
        }
    }

    private AccountBalance loadFromFile(String dataFilePath) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(Paths.get(dataFilePath)), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            return gson.fromJson(reader, AccountBalance.class);
        }
    }

    private String getDataFilePath(String tranDtime) {
        return webResourcesPath + DATA_PATH + tranDtime + ".json";
    }

    private DataNotFoundException createDataNotFoundException(String tranDtime) {
        return new DataNotFoundException("Invalid tranDtime: " + tranDtime);
    }
}