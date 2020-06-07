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
import com.tmbd.cinematics.adapter.InfoAdapter;
import com.tmbd.cinematics.adapter.ReviewsAdapter;
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

public class ReviewsFragment extends Fragment implements ApiClass.Api {

        private View rootView;
        private int movieId;
        private RecyclerView recyEvents;
        private ReviewsAdapter reviewsAdpter;
        private Fragment frag;
        String overview;
        ArrayList<EventModel> listEvents = new ArrayList<>();
        private String url = "https://api.themoviedb.org/3/movie/";


        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
            movieId = getArguments().getInt("movie_id");
            Log.e("ReviewFragment", "onCreateView: " + movieId);

            url = url + movieId + "/reviews?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US&page=1";
            Log.i("url", url);
            new ApiClass(this,getActivity()).execute(url);


            recyEvents = rootView.findViewById(R.id.recyReview);
            recyEvents.setLayoutManager(new LinearLayoutManager(getContext()
                    , LinearLayoutManager.VERTICAL, false));
            recyEvents.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

            reviewsAdpter = new ReviewsAdapter(listEvents, getActivity(), getActivity().getSupportFragmentManager());
            recyEvents.setAdapter(reviewsAdpter);
            return rootView;
        }

    @Override
    public void onPostExecute(String result) {
        try {
            JSONObject jsonObject1 = new JSONObject(result);

            JSONArray jsonArray = jsonObject1.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String author = jsonObject.getString("author");
                String content = jsonObject.getString("content");


                EventModel model = new EventModel();

                model.setAuthor(author);
                model.setContent(content);

                listEvents.add(model);

            }
            reviewsAdpter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    }













