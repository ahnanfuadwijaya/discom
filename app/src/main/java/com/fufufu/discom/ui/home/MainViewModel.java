package com.fufufu.discom.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fufufu.discom.data.model.Genre;
import com.fufufu.discom.data.source.Repository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private Repository repository;
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }
    public LiveData<ArrayList<Genre>> getGenreList(){
        return repository.getGenreList();
    }
}
