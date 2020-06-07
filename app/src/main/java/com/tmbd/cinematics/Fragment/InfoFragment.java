package com.tmbd.cinematics.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tmbd.cinematics.R;
import com.tmbd.cinematics.adapter.InfoAdapter;
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

public class InfoFragment extends Fragment  {
    private View rootView;
    private int movieId;
    private String movieTitle;
    private int Budget;
    private int Revenue;
    private String originalLanguage;
    private String originalTitle;
    private String Homepage;
    private String name;
    private RecyclerView recyEvents;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private InfoAdapter infoAdapter;
    private Fragment frag;
    private String overview;
    ArrayList<EventModel> listEvents = new ArrayList<>();
    private String url = "https://api.themoviedb.org/3/movie/";
    private String url2 = "https://api.themoviedb.org/3/movie/";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_info, container, false);
        movieId = getArguments().getInt("movie_id");
       // movieTitle = getArguments().getString("movie_title");
        Log.e("InfoFragment", "onCreateView: " + movieId);
        url2 = url2 + movieId + "?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US";
        url = url + movieId + "/similar?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US&page=1";
        Log.i("url", url);
        Log.i("url", url2);
        new ApiClass(new ApiClass.Api() {
            @Override
            public void onPostExecute(String result) {
                onPostExecute1(result);
            }
        },getActivity()).execute(url);
        new ApiClass(new ApiClass.Api() {
            @Override
            public void onPostExecute(String result) {
                onPostExecute2(result);
            }
        },getActivity()).execute(url2);
        textView = rootView.findViewById(R.id.tv_info);
        textView1 = rootView.findViewById(R.id.tv_info3);
        textView2 = rootView.findViewById(R.id.tv_info5);
        textView3 = rootView.findViewById(R.id.tv_info7);
        textView4 = rootView.findViewById(R.id.tv_info9);
        textView5 = rootView.findViewById(R.id.tv_info11);
        textView6 = rootView.findViewById(R.id.tv_info13);
        recyEvents = rootView.findViewById(R.id.recyView_info);
        recyEvents.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.HORIZONTAL, false));

        infoAdapter = new InfoAdapter(listEvents, getActivity(), getActivity().getSupportFragmentManager());
        recyEvents.setAdapter(infoAdapter);
        return rootView;
    }


    public void onPostExecute1(String result) {
        try {
            JSONObject jsonObject1 = new JSONObject(result);

            JSONArray jsonArray = jsonObject1.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String title = jsonObject.getString("title");
                String releaseDate = jsonObject.getString("release_date");
                String posterPath = jsonObject.getString("poster_path");
                int Id = jsonObject.getInt("id");
                EventModel model = new EventModel();

                model.setPosterPath(posterPath);
                model.setTitile(title);
                model.setId(Id);
                model.setReleaseDate(releaseDate);

                listEvents.add(model);

            }
            infoAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


   public void onPostExecute2(String result)
   {

       try {
           JSONObject jsonObject1 = new JSONObject(result);
           originalTitle = jsonObject1.getString("original_title");
           originalLanguage = jsonObject1.getString("original_language");
           Homepage = jsonObject1.getString("homepage");
           overview = jsonObject1.getString("overview");
           Budget = jsonObject1.getInt("budget");
           Revenue = jsonObject1.getInt("revenue");
           JSONArray jsonArray = jsonObject1.getJSONArray("production_companies");
           JSONObject jsonObject = jsonArray.getJSONObject(0);
           name = jsonObject.getString("name");


           textView.setText(overview);
           textView1.setText(originalTitle);
           textView2.setText(originalLanguage);
           textView3.setText(Homepage);
           textView4.setText(String.valueOf(Budget));
           textView5.setText(String.valueOf(Revenue));
           textView6.setText(name);


       } catch (JSONException e) {
           e.printStackTrace();
       }

   }

}











