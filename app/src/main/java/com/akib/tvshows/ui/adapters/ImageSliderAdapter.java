package com.akib.tvshows.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.akib.tvshows.R;
import com.akib.tvshows.databinding.ImageContainerBinding;
import com.akib.tvshows.models.BackDrop;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    List<BackDrop> backDrops;
    private LayoutInflater inflater;

    public ImageSliderAdapter(List<BackDrop> backDrops) {
        this.backDrops = backDrops;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        ImageContainerBinding imageContainerBinding = DataBindingUtil
                .inflate(inflater,
                        R.layout.image_container,
                        parent,false);
        return new ImageSliderViewHolder(imageContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        holder.bindSliderImage(backDrops.get(position).file_path);
    }

    @Override
    public int getItemCount() {
        return backDrops.size();
    }

    protected static class ImageSliderViewHolder extends RecyclerView.ViewHolder{
        private final ImageContainerBinding imageContainerBinding;

        public ImageSliderViewHolder(ImageContainerBinding imageContainerBinding) {
            super(imageContainerBinding.getRoot());
            this.imageContainerBinding = imageContainerBinding;
        }

        public void bindSliderImage(String imageURL){
            imageContainerBinding.setImageURL(imageURL);
        }
    }
}
