package com.akib.tvshows.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.akib.tvshows.R;
import com.akib.tvshows.databinding.CardMovieItemBinding;
import com.akib.tvshows.models.Shows;
import com.akib.tvshows.ui.listeners.CardListener;

import java.util.ArrayList;
import java.util.List;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder> {


    private final ArrayList<Shows> shows = new ArrayList<>();
    private LayoutInflater inflater;
    private CardListener cardListener;

    public TVShowsAdapter(CardListener cardListener) {
        this.cardListener = cardListener;
    }

    @NonNull
    @Override
    public TVShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        CardMovieItemBinding cardMovieItemBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.card_movie_item,
                parent,
                false);
        return new TVShowsViewHolder(cardMovieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowsViewHolder holder, int position) {
        if (position < shows.size()) {
            holder.bindShow(this.shows.get(position));
        }else {
            holder.bindShow(null);
        }
    }
    public void setShows(List<? extends Shows> shows) {
        int last = this.shows.size();
        this.shows.clear();
        notifyItemRangeRemoved(0,last);
        this.shows.addAll(shows);
        notifyItemRangeInserted(0, shows.size());
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public void addShows(List<? extends Shows> shows) {
        int last = this.shows.size();
        this.shows.addAll(shows);
        notifyItemRangeInserted(last,this.shows.size());
    }

    protected class TVShowsViewHolder extends RecyclerView.ViewHolder{

        private final CardMovieItemBinding cardBinding;

        public TVShowsViewHolder(CardMovieItemBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;

        }

        public void bindShow(Shows show){
            cardBinding.setShow(show);
            cardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardClicked(show);
                }
            });

        }
    }
}
