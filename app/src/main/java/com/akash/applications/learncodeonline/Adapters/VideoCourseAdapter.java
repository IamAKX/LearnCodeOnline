package com.akash.applications.learncodeonline.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akash.applications.learncodeonline.Model.ThumbnailModel;
import com.akash.applications.learncodeonline.Model.YouTubeThumbnailModel;
import com.akash.applications.learncodeonline.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by pratidhi on 14/6/18.
 */

public class VideoCourseAdapter extends RecyclerView.Adapter<VideoCourseAdapter.CustomViewHolder> {
    List<YouTubeThumbnailModel> list = Collections.emptyList();
    Context context;

    public VideoCourseAdapter(List<YouTubeThumbnailModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public VideoCourseAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_layout, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoCourseAdapter.CustomViewHolder holder, final int position) {
        Log.i("##akx",list.get(position).getName()+"->"+list.get(position).getImageId());

        holder.imageView.setImageResource(list.get(position).getImageId());
        holder.textView.setText(list.get(position).getName().toUpperCase());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchVideoInYoutube(list.get(position).getUrl());
            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchVideoInYoutube(list.get(position).getUrl());
            }
        });
    }

    private void launchVideoInYoutube(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail_image);
            textView = itemView.findViewById(R.id.thumbnail_title);
        }
    }
}
