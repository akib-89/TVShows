package com.akib.tvshows.ui.activities_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.akib.tvshows.R;
import com.akib.tvshows.databinding.ActivityDetailsBinding;
import com.akib.tvshows.models.BackDrop;
import com.akib.tvshows.models.Media;
import com.akib.tvshows.models.Movie;
import com.akib.tvshows.models.Shows;
import com.akib.tvshows.models.TVShow;
import com.akib.tvshows.ui.adapters.ImageSliderAdapter;
import com.akib.tvshows.viewModels.PopularTVShowsViewModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    private PopularTVShowsViewModel viewModel ;
    private ActivityDetailsBinding detailsBinding;
    private Movie movie;
    private TVShow tvShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        detailsBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.activity_details, null, false);
        super.onCreate(savedInstanceState);
        setContentView(detailsBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(PopularTVShowsViewModel.class);
        Shows show = (Shows) getIntent().getSerializableExtra("Show");
        getShowDetails(show);
        detailsBinding.backBtn.setOnClickListener(v -> onBackPressed());
    }

    private void getShowDetails(Shows show) {
        detailsBinding.setIsLoading(true);
        if (show instanceof Movie){
            getMovieDetails(show);
        }else if (show instanceof Media){
           getMediaDetails(show);
        }else if (show instanceof TVShow){
            getTVShowDetails(show);
        }


    }

    private void getTVShowDetails(Shows show) {

    }

    private void getMediaDetails(Shows show) {
        Media media = (Media) show;
        if (media.mediaType.equals("movie")){
            getMovieDetails(show);
        }else if (media.mediaType.equals("tv")){
            getTVShowDetails(show);
        }
    }

    private void getMovieDetails(Shows show) {
        viewModel.getSingleMovieDetails(show.getId()).observe(this, responses->{
            if (responses != null){
                this.movie = responses;
                if (responses.getPoster() != null){
                    setupScreen();
                }
                if (responses.getImages().backDrops != null){
                    Log.d(TAG, "getMovieDetails: backdrop found");
                    loadImageSlider(responses.getImages().backDrops);
                }
                detailsBinding.setIsLoading(false);
            }else{
                Toast.makeText(this,"could not load the data from the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupScreen(){
        if (movie != null){
            // set the screen for movie
            if (movie.getImages().posters != null){
                detailsBinding.setMovie(movie);
            }
        }else if (tvShow != null){
            //set the screen for tv show
        }else{
            //some sort of error is present
        }
    }

    private void loadImageSlider(List<BackDrop> backDrops){
        if (backDrops.size() >25){
            backDrops = backDrops.subList(0,25);
        }
        detailsBinding.sliderImagesDetailsActivity.setOffscreenPageLimit(1);
        detailsBinding.sliderImagesDetailsActivity.setAdapter(new ImageSliderAdapter(backDrops));
        setUpSlider(backDrops.size());
        detailsBinding.sliderImagesDetailsActivity.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
    }

    private void setUpSlider(int count){
        ImageView [] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i< indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.background_slider_indecator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            detailsBinding.sliderIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position){
        int childCount = detailsBinding.sliderIndicator.getChildCount();
        for (int i=0;i<childCount;i++){
            ImageView imgView = (ImageView) detailsBinding.sliderIndicator.getChildAt(i);
            if (i == position){
                imgView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.background_slider_indecator_active
                ));
            }else {
                imgView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.background_slider_indecator_inactive
                ));
            }
        }
    }
}