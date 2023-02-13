package com.fourvaluesoft.mock.openbanking.account.realname.service.impl;

import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;
import com.fourvaluesoft.mock.openbanking.account.realname.service.AccountRealNameService;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AccountRealNameServiceImpl implements AccountRealNameService {

    private static final String DATA_PATH = "/WEB-INF/data/inquiry/real_name/";

    private final String webResourcesPath;

    public AccountRealNameServiceImpl(String webResourcesPath) {
        this.webResourcesPath = webResourcesPath;
    }

    @Override
    public AccountRealName getRealName(String accountNum) throws DataNotFoundException {
        String dataFilePath = getDataFilePath(accountNum);

        try {
            return loadFromFile(dataFilePath);
        } catch (IOException | JsonIOException | JsonSyntaxException ex) {
            throw createDataNotFoundException(accountNum);
        }
    }

    private AccountRealName loadFromFile(String dataFilePath) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(Paths.get(dataFilePath)), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            return gson.fromJson(reader, AccountRealName.class);
        }
    }

    private String getDataFilePath(String accountNum) {
        return webResourcesPath + DATA_PATH + accountNum + ".json";
    }

    private DataNotFoundException createDataNotFoundException(String accountNum) {
        return new DataNotFoundException("Invalid accountNum: " + accountNum);
    }
}