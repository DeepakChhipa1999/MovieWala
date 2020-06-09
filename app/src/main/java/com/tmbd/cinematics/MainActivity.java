package com.tmbd.cinematics;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ToggleButton;

import com.tmbd.cinematics.Fragment.Anticipated;
import com.tmbd.cinematics.Fragment.NewDvd;
import com.tmbd.cinematics.Fragment.NowPlaying;
import com.tmbd.cinematics.Fragment.TopBoxOffice;
import com.tmbd.cinematics.Fragment.TopRated;
import com.tmbd.cinematics.Fragment.Tranding;
import com.tmbd.cinematics.Fragment.Upcoming;
import com.tmbd.cinematics.Fragment.UpcomingDvds;
import com.tmbd.cinematics.adapter.AnticipatedAdapter;
import com.tmbd.cinematics.util.EventModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Cinematics");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView nevigationView = findViewById(R.id.nav_view);
        nevigationView.bringToFront();
        nevigationView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NowPlaying(), "NOW PLAYING");
        adapter.addFragment(new TopBoxOffice(), "TOP BOX OFFICE");
        adapter.addFragment(new Anticipated(), "ANTICIPATED");
        adapter.addFragment(new Upcoming(), "UPCOMING");
        adapter.addFragment(new Tranding(), "TRENDING");
        adapter.addFragment(new TopRated(), "TOP RATED");
        adapter.addFragment(new NewDvd(), "NEW DVDS");
        adapter.addFragment(new UpcomingDvds(), "UPCOMING DVDS");
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener()

    {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            Log.e("MainActivity", "onNavigationItemSelected: "+id );

            if (id == R.id.movies) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            } else if (id == R.id.tv_shows) {
                startActivity(new Intent(MainActivity.this, TvShowsActivity.class));
            }/* else if (id == R.id.discover) {
                startActivity(new Intent(MainActivity.this, DiscoverActivity.class));
            }*/ else if (id == R.id.popular) {
                startActivity(new Intent(MainActivity.this, PopularPeopleActivity.class));

            } else if (id == R.id.setting) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                finish();
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    };


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);


        }
    }


}


