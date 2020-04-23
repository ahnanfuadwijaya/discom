package com.fufufu.discom.di;

import android.app.Application;

import com.fufufu.discom.data.source.Repository;

public class Injection {
    public static Repository provideRepository(Application application) {
        return Repository.getInstance();
    }
}
