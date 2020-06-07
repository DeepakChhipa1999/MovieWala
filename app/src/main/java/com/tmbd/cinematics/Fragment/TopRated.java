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
import com.tmbd.cinematics.adapter.NewDvdAdapter;
import com.tmbd.cinematics.adapter.TopRatedAdapter;
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

public class TopRated extends Fragment implements ApiClass.Api {




        private View rootView;
        private RecyclerView recyEvents;
        private TopRatedAdapter topRatedAdapter;
        private Fragment frag;
        ArrayList<EventModel> listEvents = new ArrayList<>();
        private static final String URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US&page=1";


        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_toprated, container, false);

    /*    EventModel eventModel = new EventModel(2019, "Hellboy", "Advanture", 9);
        listEvents.add(eventModel);*/
            new ApiClass(this,getActivity()).execute(URL);


            recyEvents = rootView.findViewById(R.id.recyView);
            recyEvents.setLayoutManager(new LinearLayoutManager(getContext()
                    , LinearLayoutManager.VERTICAL, false));
            recyEvents.addItemDecoration(new DividerItemDecoration(getActivity() ,DividerItemDecoration.VERTICAL));
            topRatedAdapter = new TopRatedAdapter(listEvents, getActivity(), getActivity().getSupportFragmentManager());
            recyEvents.setAdapter(topRatedAdapter);
        /*recyEvents.setOnClickListener(new View.OnClickListener() {
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

            JSONArray jsonArray = jsonObject1.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double voteAverage = jsonObject.getDouble("vote_average");
                int id = jsonObject.getInt("id");
                String title = jsonObject.getString("title");
                String posterPath = jsonObject.getString("poster_path");
                String original_language = jsonObject.getString("original_language");
                String release_date = jsonObject.getString("release_date");
                EventModel model = new EventModel();
                model.setVoteAverage(voteAverage);
                model.setPosterPath(posterPath);
                model.setTitile(title);
                model.setId(id);
                model.setOriginalLanguage(original_language);
                model.setRelease_date(release_date);
                listEvents.add(model);

            }
            topRatedAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    }










