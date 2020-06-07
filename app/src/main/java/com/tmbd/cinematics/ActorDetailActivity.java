package com.tmbd.cinematics;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.tmbd.cinematics.Fragment.ActorInfoFragment;
import com.tmbd.cinematics.Fragment.ActorMoviesFragment;
import com.tmbd.cinematics.Fragment.ActorTvShowsFragment;
import com.tmbd.cinematics.adapter.CastAdapter;
import com.tmbd.cinematics.util.EventModel;

import java.util.ArrayList;
import java.util.List;

public class ActorDetailActivity extends AppCompatActivity {

    private RecyclerView recyEvents;
    private int Id;
    private CastAdapter clickAdapter;
    ArrayList<EventModel> listEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);
        String name = getIntent().getStringExtra("actor_title");
        Log.i("name",name);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Id = getIntent().getIntExtra("id", 0);
        Log.i("id", Id + "");
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ActorInfoFragment(), "INFO");
        adapter.addFragment(new ActorMoviesFragment(), "MOVIES");
        adapter.addFragment(new ActorTvShowsFragment(), "TV SHOWS");
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

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
            Bundle bundle = new Bundle();
            bundle.putInt("id", Id);
            fragment.setArguments(bundle);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);


        }
    }


}


