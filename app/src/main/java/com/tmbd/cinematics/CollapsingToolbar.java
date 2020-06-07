package com.tmbd.cinematics;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tmbd.cinematics.util.EventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CollapsingToolbar extends AppCompatActivity {
    private String posterPath;
    private String backdroPath;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private String ReleaseDate;
    private String Title;
    private String Tagline;
    private Integer Duration;
    ArrayList<EventModel> listEvents = new ArrayList<>();
    private static final String URL = "https://api.themoviedb.org/3/movie/420818?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US\n";
    private String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("The Lion King");
        setSupportActionBar(toolbar);
       final ImageView imageView = findViewById(R.id.expandedImage);
       final ImageView imageView1 = findViewById(R.id.image_fram);
       textView = findViewById(R.id.tv_film_year);
       textView1 = findViewById(R.id.tv_film_duration);
       textView2 = findViewById(R.id.tv_film_name);
       textView3 = findViewById(R.id.tv_film_zoner);



       OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(URL)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                Log.e("collapsing",result);
                try {
                    JSONObject jsonObject1 = new JSONObject(result);
                   String title = jsonObject1.getString("original_title");
                   posterPath = jsonObject1.getString("poster_path");
                   backdroPath = jsonObject1.getString("backdrop_path");
                   Duration = jsonObject1.getInt("runtime");
                   ReleaseDate = jsonObject1.getString("release_date");
                   Title = jsonObject1.getString("original_title");
                   Tagline = jsonObject1.getString("tagline");
         /*          JSONArray jsonArray = jsonObject1.getJSONArray("genres");
                   for (int i=1;i<jsonArray.length();i++) {
                       String Genres = jsonArray.getJSONObject("1");
                   }*/


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(ReleaseDate);
                            textView1.setText(String.valueOf(Duration));
                            textView2.setText(Title);
                            textView3.setText(Tagline);
                            Glide.with(CollapsingToolbar.this).load(BASE_IMAGE_URL + backdroPath).into(imageView);
                            Glide.with(CollapsingToolbar.this).load(BASE_IMAGE_URL + posterPath).into(imageView1);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
