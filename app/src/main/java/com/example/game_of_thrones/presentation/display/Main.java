package com.example.game_of_thrones.presentation.display;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.game_of_thrones.R;
import com.example.game_of_thrones.presentation.display.favorite.fragment.FavoriteGotCharacterFragment;
import com.example.game_of_thrones.presentation.display.list.fragment.ListFragment;

public class Main extends AppCompatActivity {

    private ViewPager viewPager;
    //private FragmentStateAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewPagerAndTabs();

        /*TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("FRAGMENT " + (position + 1))
        ).attach();*/
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.view_pager);

        final ListFragment listFrag = ListFragment.newInstance();
        final FavoriteGotCharacterFragment favoriteFrag = FavoriteGotCharacterFragment.newInstance();

        //pagerAdapter = new FragmentStateAdapter(this) {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return listFrag;
                } else {
                    return favoriteFrag;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return listFrag.name;
                }
                return favoriteFrag.name;
            }

            //viewPager.setAdapter(pagerAdapter);
        });
    }
}
