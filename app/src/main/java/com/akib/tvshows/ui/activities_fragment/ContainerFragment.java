package com.akib.tvshows.ui.activities_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akib.tvshows.R;
import com.akib.tvshows.databinding.FragmentContainerBinding;
import com.akib.tvshows.models.Booleans;
import com.akib.tvshows.models.Headings;
import com.akib.tvshows.models.Shows;
import com.akib.tvshows.ui.adapters.TVShowsAdapter;
import com.akib.tvshows.ui.listeners.CardListener;
import com.akib.tvshows.viewModels.PopularTVShowsViewModel;


public class ContainerFragment extends Fragment implements CardListener {
    private static final String TAG = "Container";
    private final boolean isMovieTab;
    private FragmentContainerBinding containerBinding;
    private final int[] current;
    private final int[] last;

    private PopularTVShowsViewModel viewModel;
    private TVShowsAdapter adapter1;
    private TVShowsAdapter adapter2;
    private TVShowsAdapter adapter3;
    private TVShowsAdapter adapter4;
    private TVShowsAdapter adapter5;

    RecyclerView trending;
    RecyclerView topRating;
    RecyclerView popular;
    RecyclerView type1;
    RecyclerView typ2;

    public ContainerFragment(boolean isMovieTab) {
        this.isMovieTab = isMovieTab;
        current = new int[]{1, 1, 1, 1, 1};
        last = new int[5];
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        containerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_container, null, false);
        return containerBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PopularTVShowsViewModel.class);
        containerBinding.setBools(new Booleans());

        //setting up the adapters
        adapter1 = new TVShowsAdapter(this);
        adapter2 = new TVShowsAdapter(this);
        adapter3 = new TVShowsAdapter(this);
        adapter4 = new TVShowsAdapter(this);
        adapter5 = new TVShowsAdapter(this);


        trending = containerBinding.trendingRecyclerContainerFragment;
        topRating = containerBinding.topRatedRecyclerContainerFragment;
        popular = containerBinding.popularRecyclerContainerFragment;
        type1 = containerBinding.head1RecyclerContainerFragment;
        typ2 = containerBinding.head2RecyclerContainerFragment;

        trending.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        trending.setAdapter(adapter1);

        topRating.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        topRating.setAdapter(adapter2);

        popular.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        popular.setAdapter(adapter3);

        type1.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        type1.setAdapter(adapter4);

        typ2.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        typ2.setAdapter(adapter5);

        if (isMovieTab) {
            getMoviesList();
            setListenerMovie();
            containerBinding.setHead(new Headings("Trending", "Top Rated", "Popular", "Upcoming", "Now Playing"));
        } else {
            getTVShowsList();
            setListenerTV();
            containerBinding.setHead(new Headings("Trending", "Top Rated", "Popular", "Currently Airing on Different platforms", "Airing Today"));
        }


        topRating.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void getTVShowsList() {
        containerBinding.shimmer1.startShimmer();
        containerBinding.getBools().rc1.set(true);
        containerBinding.shimmer2.startShimmer();
        containerBinding.getBools().rc2.set(true);
        containerBinding.shimmer3.startShimmer();
        containerBinding.getBools().rc3.set(true);
        containerBinding.shimmer4.startShimmer();
        containerBinding.getBools().rc4.set(true);
        containerBinding.shimmer5.startShimmer();
        containerBinding.getBools().rc5.set(true);
        viewModel.getTrendingTVShows("week", 1).observe(getViewLifecycleOwner(), responses -> {
            adapter1.setShows(responses.getShows());
            last[0] = responses.getTotalPage();
            containerBinding.shimmer1.stopShimmer();
            containerBinding.getBools().rc1.set(false);
        });

        viewModel.getTopRatedTVShows(1).observe(getViewLifecycleOwner(), responses -> {
            adapter2.setShows(responses.getShows());
            last[1] = responses.getTotalPage();
            containerBinding.shimmer2.stopShimmer();
            containerBinding.getBools().rc2.set(false);
        });
        viewModel.getPopularTVShows(1).observe(getViewLifecycleOwner(), responses -> {
            adapter3.setShows(responses.getShows());
            last[2] = responses.getTotalPage();
            containerBinding.shimmer3.stopShimmer();
            containerBinding.getBools().rc3.set(false);
        });
        viewModel.getOnAirTVShows(1).observe(getViewLifecycleOwner(), responses -> {
            adapter4.setShows(responses.getShows());
            last[3] = responses.getTotalPage();
            containerBinding.shimmer4.stopShimmer();
            containerBinding.getBools().rc4.set(false);
        });
        viewModel.getTvShowsAiringToday(1).observe(getViewLifecycleOwner(), responses -> {
            adapter5.setShows(responses.getShows());
            last[4] = responses.getTotalPage();
            containerBinding.shimmer5.stopShimmer();
            containerBinding.getBools().rc5.set(false);
        });
    }

    private void getMoviesList() {
        containerBinding.trendingRecyclerContainerFragment.setVisibility(View.GONE);
        containerBinding.topRatedRecyclerContainerFragment.setVisibility(View.GONE);
        containerBinding.popularRecyclerContainerFragment.setVisibility(View.GONE);
        containerBinding.head1RecyclerContainerFragment.setVisibility(View.GONE);
        containerBinding.head2RecyclerContainerFragment.setVisibility(View.GONE);
        containerBinding.shimmer1.startShimmer();
        containerBinding.getBools().rc1.set(true);
        containerBinding.shimmer2.startShimmer();
        containerBinding.getBools().rc2.set(true);
        containerBinding.shimmer3.startShimmer();
        containerBinding.getBools().rc3.set(true);
        containerBinding.shimmer4.startShimmer();
        containerBinding.getBools().rc4.set(true);
        containerBinding.shimmer5.startShimmer();
        containerBinding.getBools().rc5.set(true);
        viewModel.getTrendingMovies("week", 1).observe(getViewLifecycleOwner(), responses -> {
            adapter1.setShows(responses.getShows());
            last[0] = responses.getTotalPage();
            containerBinding.trendingRecyclerContainerFragment.setVisibility(View.VISIBLE);
            containerBinding.shimmer1.stopShimmer();
            containerBinding.getBools().rc1.set(false);
        });

        viewModel.getTopRatedMovies(1).observe(getViewLifecycleOwner(), responses -> {
            adapter2.setShows(responses.getShows());
            last[1] = responses.getTotalPage();
            containerBinding.shimmer2.stopShimmer();
            containerBinding.getBools().rc2 .set(false);
            containerBinding.topRatedRecyclerContainerFragment.setVisibility(View.VISIBLE);

        });

        viewModel.getPopularMovies(1).observe(getViewLifecycleOwner(), responses -> {
            adapter3.setShows(responses.getShows());
            last[2] = responses.getTotalPage();
            containerBinding.shimmer3.stopShimmer();
            containerBinding.getBools().rc3.set(false);
            containerBinding.popularRecyclerContainerFragment.setVisibility(View.VISIBLE);

        });

        viewModel.getUpcomingMovies(1).observe(getViewLifecycleOwner(), responses -> {
            adapter4.setShows(responses.getShows());
            last[3] = responses.getTotalPage();

            containerBinding.shimmer4.stopShimmer();
            containerBinding.getBools().rc4.set(false);
            containerBinding.head1RecyclerContainerFragment.setVisibility(View.VISIBLE);
        });

        viewModel.getNowPlayingMovies(1).observe(getViewLifecycleOwner(), responses -> {
            adapter5.setShows(responses.getShows());
            last[4] = responses.getTotalPage();
            containerBinding.shimmer5.stopShimmer();
            containerBinding.getBools().rc5.set(false);
            containerBinding.head2RecyclerContainerFragment.setVisibility(View.VISIBLE);

        });
    }

    private void setListenerMovie() {
        trending.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[0] < last[0]) {
                        current[0] += 1;
                        containerBinding.shimmer1.startShimmer();
                        containerBinding.getBools().rc1.set(true);
                        viewModel.getTrendingMovies("week", current[0]).observe(getViewLifecycleOwner(), responses -> {
                            adapter1.addShows(responses.getShows());
                            last[0] = responses.getTotalPage();
                            containerBinding.getBools().rc1.set(false);
                            containerBinding.shimmer1.stopShimmer();
                        });

                    }
                }
            }
        });

        topRating.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[1] < last[1]) {
                        current[1] += 1;
                        containerBinding.shimmer2.startShimmer();
                        containerBinding.getBools().rc2.set(true);
                        viewModel.getTopRatedMovies(current[1]).observe(getViewLifecycleOwner(), responses -> {
                            adapter2.addShows(responses.getShows());
                            last[1] = responses.getTotalPage();
                            containerBinding.getBools().rc2.set(false);
                            containerBinding.shimmer2.stopShimmer();
                        });

                    }
                }
            }
        });


        popular.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[2] < last[2]) {
                        current[2] += 1;
                        containerBinding.shimmer3.startShimmer();
                        containerBinding.getBools().rc3.set(true);
                        viewModel.getPopularMovies(current[2]).observe(getViewLifecycleOwner(), responses -> {
                            adapter3.addShows(responses.getShows());
                            last[2] = responses.getTotalPage();
                            containerBinding.getBools().rc3.set(false);
                            containerBinding.shimmer3.stopShimmer();
                        });
                    }
                }
            }
        });

        type1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[3] < last[3]) {
                        current[3] += 1;
                        containerBinding.getBools().rc4.set(true);
                        containerBinding.shimmer2.startShimmer();
                        viewModel.getUpcomingMovies(current[3]).observe(getViewLifecycleOwner(), responses -> {
                            adapter4.addShows(responses.getShows());
                            last[3] = responses.getTotalPage();
                            containerBinding.getBools().rc4.set(false);
                            containerBinding.shimmer4.stopShimmer();
                        });
                    }
                }
            }
        });

        typ2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[4] < last[4]) {
                        current[4] += 1;
                        containerBinding.getBools().rc5.set(true);
                        containerBinding.shimmer5.stopShimmer();
                        viewModel.getNowPlayingMovies(current[4]).observe(getViewLifecycleOwner(), responses -> {
                            adapter5.addShows(responses.getShows());
                            last[4] = responses.getTotalPage();
                            containerBinding.getBools().rc5.set(false);
                            containerBinding.shimmer5.stopShimmer();
                        });
                    }
                }
            }
        });

    }

    public void setListenerTV(){
        trending.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[0] < last[0]) {
                        current[0] += 1;
                        containerBinding.shimmer1.startShimmer();
                        containerBinding.getBools().rc1.set(true);
                        viewModel.getTrendingTVShows("week", current[0]).observe(getViewLifecycleOwner(), responses -> {
                            adapter1.addShows(responses.getShows());
                            last[0] = responses.getTotalPage();
                            containerBinding.getBools().rc1.set(false);
                            containerBinding.shimmer1.stopShimmer();
                        });

                    }
                }
            }
        });

        topRating.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[1] < last[1]) {
                        current[1] += 1;
                        containerBinding.shimmer2.startShimmer();
                        containerBinding.getBools().rc2.set(true);
                        viewModel.getTopRatedTVShows(current[1]).observe(getViewLifecycleOwner(), responses -> {
                            adapter2.addShows(responses.getShows());
                            last[1] = responses.getTotalPage();
                            containerBinding.getBools().rc2.set(false);
                            containerBinding.shimmer2.stopShimmer();
                        });

                    }
                }
            }
        });


        popular.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[2] < last[2]) {
                        current[2] += 1;
                        containerBinding.shimmer3.startShimmer();
                        containerBinding.getBools().rc3.set(true);
                        viewModel.getPopularTVShows(current[2]).observe(getViewLifecycleOwner(), responses -> {
                            adapter3.addShows(responses.getShows());
                            last[2] = responses.getTotalPage();
                            containerBinding.getBools().rc3.set(false);
                            containerBinding.shimmer3.stopShimmer();
                        });
                    }
                }
            }
        });

        type1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[3] < last[3]) {
                        current[3] += 1;
                        containerBinding.shimmer4.startShimmer();
                        containerBinding.getBools().rc4.set(true);
                        viewModel.getOnAirTVShows(current[3]).observe(getViewLifecycleOwner(), responses -> {
                            adapter4.addShows(responses.getShows());
                            last[3] = responses.getTotalPage();
                            containerBinding.getBools().rc4.set(false);
                            containerBinding.shimmer4.stopShimmer();
                        });
                    }
                }
            }
        });

        typ2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    if (current[4] < last[4]) {
                        current[4] += 1;
                        containerBinding.shimmer5.startShimmer();
                        containerBinding.getBools().rc5.set(true);
                        viewModel.getTvShowsAiringToday(current[4]).observe(getViewLifecycleOwner(), responses -> {
                            adapter5.addShows(responses.getShows());
                            last[4] = responses.getTotalPage();
                            containerBinding.getBools().rc5.set(false);
                            containerBinding.shimmer5.stopShimmer();
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onCardClicked(Shows show) {
            //here we will pass the movie to the next activity
            Log.d(TAG, "onCardClicked: calling the movie details " + show.toString());
            Intent intent = new Intent(requireActivity().getApplicationContext(), DetailsActivity.class);
            intent.putExtra("Show", show);
            startActivity(intent);
    }
}