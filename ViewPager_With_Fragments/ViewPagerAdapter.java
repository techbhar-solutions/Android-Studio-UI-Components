package com.example.retrofitexample;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HomeFragment(); // Pehla Page (Left)
        } else {
            return new ProfileFragment(); // Dusra Page (Right)
        }
    }
    // 2. Yahan batao total kitne pages hain
    @Override
    public int getItemCount() {
        return 2; // Humare paas 2 fragments hain
    }
}
