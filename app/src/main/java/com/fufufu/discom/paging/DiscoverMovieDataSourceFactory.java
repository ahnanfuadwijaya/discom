package com.fufufu.discom.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class DiscoverMovieDataSourceFactory extends DataSource.Factory {
    private int genreId;
    private MutableLiveData<DiscoverMovieDataSource> mutableLiveData;

    public DiscoverMovieDataSourceFactory(int genreId){
        mutableLiveData = new MutableLiveData<>();
        this.genreId = genreId;
    }

    @Override
    public DataSource create() {
        DiscoverMovieDataSource movieDataSource = new DiscoverMovieDataSource(genreId);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
