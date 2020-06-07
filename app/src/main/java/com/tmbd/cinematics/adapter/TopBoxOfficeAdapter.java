package com.tmbd.cinematics.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tmbd.cinematics.R;
import com.tmbd.cinematics.ScrollingActivity;
import com.tmbd.cinematics.util.EventModel;

import java.util.ArrayList;

public class TopBoxOfficeAdapter extends RecyclerView.Adapter<TopBoxOfficeAdapter.ViewHolder> {

        private String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

        private ArrayList<EventModel> listData;
        private Context context;
        private FragmentManager fragmentManager;

        public TopBoxOfficeAdapter(ArrayList<EventModel> data, Context context, FragmentManager fragmentManager) {
            listData = data;
            this.context = context;
            this.fragmentManager = fragmentManager;
        }


        @NonNull
        @Override
        public TopBoxOfficeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_event, parent, false);
            return new ViewHolder(view);
        }

        public void onBindViewHolder(@NonNull final TopBoxOfficeAdapter.ViewHolder holder, final int position) {

            final EventModel value = listData.get(position);

            holder.tvTitle.setText(String.valueOf(value.getVoteAverage()));
            holder.tvTitle2.setText(value.getTitile());
            holder.tvTitle3.setText(value.getOriginalLanguage());
            holder.tvTitle4.setText(value.getRelease_date());

            Glide.with(context).load(BASE_IMAGE_URL+value.getPosterPath()).into(holder.imageview);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ScrollingActivity.class);
                    intent.putExtra("movie_id",value.getId());
                    intent.putExtra("movie_title",value.getTitile());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public TextView tvTitle3;
            public TextView tvTitle;
            public TextView tvTitle2;
            public TextView tvTitle4;
            public ImageView imageview;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                tvTitle = view.findViewById(R.id.tv_event_name);
                tvTitle2 = view.findViewById(R.id.tv_picture_name);
                tvTitle3 = view.findViewById(R.id.tv_picture_type);
                tvTitle4 = view.findViewById(R.id.tv_picture_rating);

                imageview = view.findViewById(R.id.image_event);

            }


        }


    }




