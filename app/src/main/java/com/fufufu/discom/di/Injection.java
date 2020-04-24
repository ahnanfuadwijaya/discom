package com.fufufu.discom.di;

import com.fufufu.discom.data.source.Repository;

public class Injection {
    public static Repository provideRepository() {
        return Repository.getInstance();
    }
}
