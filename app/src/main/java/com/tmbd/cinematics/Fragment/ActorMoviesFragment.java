package com.tmbd.cinematics.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmbd.cinematics.R;
import com.tmbd.cinematics.adapter.ActorMoviesAdapter;
import com.tmbd.cinematics.adapter.AnticipatedAdapter;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class ActorMoviesFragment extends Fragment implements ApiClass.Api {


    private View rootView;
    private RecyclerView recyEvents;
    private ActorMoviesAdapter actorMoviesAdapter;
    private Fragment frag;
    private int Id;
    ArrayList<EventModel> listEvents = new ArrayList<>();
    private String url = "https://api.themoviedb.org/3/person/";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_actor_movies, container, false);
        Id = getArguments().getInt("id");
        // movieTitle = getArguments().getString("movie_title");
        Log.e("ActorMoviesFragment", "onCreateView: " + Id);
        url = url + Id + "/movie_credits?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US";
        Log.i("url", url);
    /*    EventModel eventModel = new EventModel(2019, "Hellboy", "Advanture", 9);
        listEvents.add(eventModel);*/
        new ApiClass(this,getActivity()).execute(url);


        recyEvents = rootView.findViewById(R.id.recyView_actor_movies);
        recyEvents.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        recyEvents.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        actorMoviesAdapter = new ActorMoviesAdapter(listEvents, getActivity(), getActivity().getSupportFragmentManager());
        recyEvents.setAdapter(actorMoviesAdapter);
        /*recyEvents.setOnClickListener(new View`.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame_layout, new secondFragment());
                fragmentTransaction.commit();
            }

        });*/
        return rootView;


    }

    @Override
    public void onPostExecute(String result) {

        try {
            JSONObject jsonObject1 = new JSONObject(result);

            JSONArray jsonArray = jsonObject1.getJSONArray("cast");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String title = jsonObject.getString("original_title");
                String posterPath = jsonObject.getString("poster_path");
                String character = jsonObject.getString("character");
                int id = jsonObject.getInt("id");
                String releasedate = jsonObject.optString("release_date");
                EventModel model = new EventModel();
                model.setTitile(title);
                model.setPosterPath(posterPath);
                model.setCharacter(character);
                model.setReleaseDate(releasedate);
                model.setId(id);
                listEvents.add(model);

            }

            actorMoviesAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}










