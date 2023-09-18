package com.akib.tvshows.ui.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.akib.tvshows.ui.activities_fragment.ContainerFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private final int tabCount;
    private ContainerFragment current;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tabCount = behavior;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (current != object){
            current = (ContainerFragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ContainerFragment(true);
            case 1:
                return new ContainerFragment(false);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public ContainerFragment getCurrent() {
        return current;
    }
}
