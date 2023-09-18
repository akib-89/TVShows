package com.akib.tvshows.ui.adapters;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.akib.tvshows.utils.ImageURLBuilder;
import com.bumptech.glide.Glide;

import java.util.Locale;

public class BindingAdapters {
    @BindingAdapter("android:setPosterImageFromURL")
    public static void setPosterImageFromURL(ImageView imageView, String URL){
        if (URL != null) {
            String fullURL = ImageURLBuilder.getBuilder().getFullImageUrl(URL, "w185");
            Glide.with(imageView)
                    .load(fullURL)
                    .centerCrop()
                    .into(imageView);
        }
    }
    @BindingAdapter("android:showLanguage")
    public static void showLanguage(TextView tv, String iso_639_code){
        if (iso_639_code != null) {
            Locale locale = new Locale(iso_639_code);
            tv.setText(locale.getDisplayLanguage());
        }
    }
    @BindingAdapter("android:setBackdropImageFromURL")
    public static void setBackdropImageFromURL(ImageView imageView, String URL){
        if (URL != null){
            String fullURL = ImageURLBuilder.getBuilder().getFullImageUrl(URL, "w780");
            Glide.with(imageView)
                    .load(fullURL)
                    .centerCrop()
                    .into(imageView);
        }
    }



}
