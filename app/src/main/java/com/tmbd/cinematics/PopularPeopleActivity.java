package com.tmbd.cinematics;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tmbd.cinematics.Fragment.Anticipated;
import com.tmbd.cinematics.adapter.AnticipatedAdapter;
import com.tmbd.cinematics.adapter.PopularPeopleAdapter;
import com.tmbd.cinematics.util.ApiClass;
import com.tmbd.cinematics.util.EventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PopularPeopleActivity extends AppCompatActivity implements ApiClass.Api {
    private View rootView;
    private RecyclerView recyEvents;
    private PopularPeopleAdapter popularPeopleAdapter;
    private Fragment frag;
    ArrayList<EventModel> listEvents = new ArrayList<>();
    private static final String URL = "https://api.themoviedb.org/3/person/popular?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_people);


        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Popular People");
        // toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new ApiClass(this,this).execute(URL);


        recyEvents = findViewById(R.id.recyView1);
        recyEvents.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));
        recyEvents.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        popularPeopleAdapter = new PopularPeopleAdapter(listEvents, this);
        recyEvents.setAdapter(popularPeopleAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostExecute(String result) {
        try {
            JSONObject jsonObject1 = new JSONObject(result);

            JSONArray jsonArray = jsonObject1.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String name = jsonObject.getString("name");
                String posterPath = jsonObject.getString("profile_path");
                int Id = jsonObject.getInt("id");
                EventModel model = new EventModel();

                model.setPosterPath(posterPath);
                model.setName(name);
                model.setId(Id);
                listEvents.add(model);

            }
            popularPeopleAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }









