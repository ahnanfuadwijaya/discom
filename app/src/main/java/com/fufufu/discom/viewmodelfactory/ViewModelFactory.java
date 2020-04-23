package com.fufufu.discom.viewmodelfactory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fufufu.discom.data.source.Repository;
import com.fufufu.discom.di.Injection;
import com.fufufu.discom.ui.detail.DetailViewModel;
import com.fufufu.discom.ui.discover.DiscoverViewModel;
import com.fufufu.discom.ui.home.MainViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final Repository repository;
    private ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(repository);
        }
        else if (modelClass.isAssignableFrom(DiscoverViewModel.class)) {
            //noinspection unchecked
            return (T) new DiscoverViewModel(repository);
        }
        else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
