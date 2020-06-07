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
import com.tmbd.cinematics.ActorDetailActivity;
import com.tmbd.cinematics.R;
import com.tmbd.cinematics.util.EventModel;

import java.util.ArrayList;


public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {


    private String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    private ArrayList<EventModel> listData;
    private Context context;
    private FragmentManager fragmentManager;

    public CastAdapter(ArrayList<EventModel> data, Context context, FragmentManager fragmentManager) {
        listData = data;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public CastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list2, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull final CastAdapter.ViewHolder holder, final int position) {

        final EventModel value = listData.get(position);

        holder.tvTitle.setText(value.getName());
        holder.tvTitle1.setText(value.getCharacter());


        Glide.with(context).load(BASE_IMAGE_URL + value.getPosterPath()).into(holder.imageview);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActorDetailActivity.class);
                intent.putExtra("id", value.getId());
                intent.putExtra("actor_title", value.getName());
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
        public TextView tvTitle;
        public TextView tvTitle1;
        public ImageView imageview;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = view.findViewById(R.id.tv_popularPeople);
            tvTitle1 = view.findViewById(R.id.tv_popularPeople1);
            imageview = view.findViewById(R.id.profile_image);

        }


    }

}




