package com.tmbd.cinematics.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.tmbd.cinematics.R;
import com.tmbd.cinematics.adapter.InfoAdapter;
import com.tmbd.cinematics.util.EventModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ActorInfoFragment extends Fragment {


    private View rootView;
    private int Id;
    String posterPath;
    private int Gender;
    private String Born;
    private String Homepage;
    private String name;
    private RecyclerView recyEvents;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private ImageView imageView;
    private InfoAdapter infoAdapter;
    private Fragment frag;
    private String Biography;
    private String Birthplace;
    public String known;
    ArrayList<EventModel> listEvents = new ArrayList<>();
    private String url = "https://api.themoviedb.org/3/person/";
    private String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_actor_info, container, false);
        Id = getArguments().getInt("id");
        // movieTitle = getArguments().getString("movie_title");
        Log.e("ActorInfoFragment", "onCreateView: " + Id);
        url = url + Id + "?api_key=8c50e8512e62d0f6075b091976afdde1&language=en-US";
        Log.i("url", url);
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
// Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                    //   String result = response.toString();
                        /*Log.e("ActorInfo", "onResponse: "+response );*/
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            Born = jsonObject1.getString("birthday");
                            Gender = jsonObject1.getInt("gender");
                            Homepage = jsonObject1.getString("homepage");
                            Biography = jsonObject1.getString("biography");
                            Birthplace = jsonObject1.getString("place_of_birth");
                            posterPath = jsonObject1.getString("profile_path");
                            JSONArray jsonArray = jsonObject1.getJSONArray("also_known_as");
                            known = "";
                            for (int i = 0; i < jsonArray.length(); i++) {
                                known = known + jsonArray.getString(i);
                            }

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(Biography);
                                    textView1.setText(Born);
                                    textView2.setText(String.valueOf(Gender));
                                    textView3.setText(Birthplace);

                                    textView4.setText(Homepage);
                                    textView5.setText(known);

                                    Glide.with(getActivity()).load(BASE_IMAGE_URL + posterPath).into(imageView);
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");

            }
      });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

      /*  OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("response", (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + "");
                String result = response.body().string();
                try {
                    JSONObject jsonObject1 = new JSONObject(result);
                    Born = jsonObject1.getString("birthday");
                    Gender = jsonObject1.getInt("gender");
                    Homepage = jsonObject1.getString("homepage");
                    Biography = jsonObject1.getString("biography");
                    Birthplace = jsonObject1.getString("place_of_birth");
                    posterPath = jsonObject1.getString("profile_path");
                    JSONArray jsonArray = jsonObject1.getJSONArray("also_known_as");
                    known = "";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        known = known + jsonArray.getString(i);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(Biography);
                            textView1.setText(Born);
                            textView2.setText(String.valueOf(Gender));
                            textView3.setText(Birthplace);

                            textView4.setText(Homepage);
                            textView5.setText(known);

                            Glide.with(getActivity()).load(BASE_IMAGE_URL + posterPath).into(imageView);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/

        // new ApiClass(this,getActivity()).execute(url);
        textView = rootView.findViewById(R.id.tv_actor_info);
        textView1 = rootView.findViewById(R.id.tv_actor_info3);
        textView2 = rootView.findViewById(R.id.tv_actor_info5);
        textView3 = rootView.findViewById(R.id.tv_actor_info7);
        textView4 = rootView.findViewById(R.id.tv_actor_info9);
        textView5 = rootView.findViewById(R.id.tv_actor_info11);
        imageView = rootView.findViewById(R.id.image_actor_info);
        return rootView;
    }
}
/*
    @Override
    public void onPostExecute(String result) {

        try {
            JSONObject jsonObject1 = new JSONObject(result);
            Born = jsonObject1.getString("birthday");
            Gender = jsonObject1.getInt("gender");
            Homepage = jsonObject1.getString("homepage");
            Biography = jsonObject1.getString("biography");
            Birthplace = jsonObject1.getString("place_of_birth");
            posterPath = jsonObject1.getString("profile_path");
            JSONArray jsonArray = jsonObject1.getJSONArray("also_known_as");
            String known = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                known = known + jsonArray.getString(i);
            }


            textView.setText(Biography);
            textView1.setText(Born);
            textView2.setText(String.valueOf(Gender));
            textView3.setText(Birthplace);

            textView4.setText(Homepage);
            textView5.setText(known);

            Glide.with(getActivity()).load(BASE_IMAGE_URL+posterPath).into(imageView);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

/*
    class HttpGetRequest extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog = new ProgressDialog(getActivity());
        private static final String REQUEST_METHOD = "GET";
        private static final int READ_TIMEOUT = 15000;
        private static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                java.net.URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                if (connection != null) {
                    connection.disconnect();
                }
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("api result", result);

            try {
                JSONObject jsonObject1 = new JSONObject(result);
                Born = jsonObject1.getString("birthday");
                Gender = jsonObject1.getInt("gender");
                Homepage = jsonObject1.getString("homepage");
                Biography = jsonObject1.getString("biography");
                Birthplace = jsonObject1.getString("place_of_birth");
                posterPath = jsonObject1.getString("profile_path");
                JSONArray jsonArray = jsonObject1.getJSONArray("also_known_as");
                String known = "";
                for (int i = 0; i < jsonArray.length(); i++) {
                    known = known + jsonArray.getString(i);
                }


                textView.setText(Biography);
                textView1.setText(Born);
                textView2.setText(String.valueOf(Gender));
                textView3.setText(Birthplace);

                textView4.setText(Homepage);
                textView5.setText(known);

               Glide.with(getActivity()).load(BASE_IMAGE_URL+posterPath).into(imageView);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }*/














