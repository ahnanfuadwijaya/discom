package com.fufufu.discom.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fufufu.discom.R;
import com.fufufu.discom.data.model.Genre;
import com.fufufu.discom.viewmodelfactory.ViewModelFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvGenreList;
    private GenreAdapter genreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.title_discom_discover_movies));
        rvGenreList = findViewById(R.id.rv_genre_list);
        rvGenreList.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        rvGenreList.setHasFixedSize(true);
        genreAdapter = new GenreAdapter();
        MainViewModel mainViewModel = obtainViewModel(this);
        mainViewModel.getGenreList().observe(this, new Observer<ArrayList<Genre>>() {
            @Override
            public void onChanged(ArrayList<Genre> genres) {
                genreAdapter.setGenreList(genres);
                rvGenreList.setAdapter(genreAdapter);
            }
        });
    }

    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }
}
