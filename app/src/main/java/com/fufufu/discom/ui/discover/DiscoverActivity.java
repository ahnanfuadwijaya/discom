package com.fufufu.discom.ui.discover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fufufu.discom.R;
import com.fufufu.discom.data.model.Movie;
import com.fufufu.discom.viewmodelfactory.ViewModelFactory;

public class DiscoverActivity extends AppCompatActivity {
    private RecyclerView rvDiscoverMovieList;
    private DiscoverPagedAdapter discoverAdapter;
    private DiscoverViewModel discoverViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String genre = getIntent().getStringExtra("genre_name");
        int genreId = getIntent().getIntExtra("genre_id", 0);
        setTitle("DiscoM - "+genre);
        setContentView(R.layout.activity_discover);
        rvDiscoverMovieList = findViewById(R.id.rv_discover_movie_list);
        discoverAdapter = new DiscoverPagedAdapter();
        rvDiscoverMovieList.setLayoutManager(new LinearLayoutManager(this));
        discoverViewModel = obtainViewModel(this);
        discoverViewModel.getDiscoverMoviePagedList(genreId).observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                discoverAdapter.submitList(movies);
                rvDiscoverMovieList.setAdapter(discoverAdapter);
            }
        });
    }

    private static DiscoverViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(DiscoverViewModel.class);
    }
}
