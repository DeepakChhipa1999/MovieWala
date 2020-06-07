package com.tmbd.cinematics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tmbd.cinematics.R;
import com.tmbd.cinematics.util.EventModel;

import java.util.ArrayList;



    public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {


        private String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

        private ArrayList<EventModel> listData;
        private Context context;
        private FragmentManager fragmentManager;

        public ReviewsAdapter(ArrayList<EventModel> data, Context context,FragmentManager fragmentManager) {
            listData = data;
            this.context = context;
            this.fragmentManager = fragmentManager;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_review, parent, false);
            return new ViewHolder(view);
        }


        public void onBindViewHolder(@NonNull final ReviewsAdapter.ViewHolder holder, final int position) {

            final EventModel value = listData.get(position);

            holder.tvTitle.setText(value.getAuthor());
            holder.tvTitle1.setText(value.getContent());





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




            public ViewHolder(View view) {
                super(view);
                mView = view;
                tvTitle = view.findViewById(R.id.tv_reviews);
                tvTitle1 = view.findViewById(R.id.tv_reviews1);





            }


        }

    }






