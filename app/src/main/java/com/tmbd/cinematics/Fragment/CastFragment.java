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
import com.tmbd.cinematics.adapter.CastAdapter;
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

public class CastFragment extends Fragment implements ApiClass.Api {


    private View rootView;
    private RecyclerView recyEvents;
    private CastAdapter castAdapter;
    private int movieId;
    private Fragment frag;
    ArrayList<EventModel> listEvents = new ArrayList<>();
    private String url = "https://api.themoviedb.org/3/movie/";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cast, container, false);
        movieId = getArguments().getInt("movie_id");
        Log.e("Cast Fragment", "onCreateView: "+movieId );

        url = url + movieId + "/credits?api_key=8c50e8512e62d0f6075b091976afdde1";
        Log.i("url",url);
        new ApiClass(this,getActivity()).execute(url);


        recyEvents = rootView.findViewById(R.id.recyCast);
        recyEvents.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        recyEvents.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        castAdapter = new CastAdapter(listEvents, getActivity(), getActivity().getSupportFragmentManager());
        recyEvents.setAdapter(castAdapter);
return rootView;
    }

    @Override
    public void onPostExecute(String result) {

        try {
            JSONObject jsonObject1 = new JSONObject(result);

            JSONArray jsonArray = jsonObject1.getJSONArray("cast");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String character = jsonObject.getString("character");
                String name = jsonObject.getString("name");
                int Id = jsonObject.getInt("id");
                String posterPath = jsonObject.getString("profile_path");


                EventModel model = new EventModel();

                model.setPosterPath(posterPath);
                model.setCharacter(character);
                model.setName(name);
                model.setId(Id);

                listEvents.add(model);

            }
            castAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}





