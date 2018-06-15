package com.akash.applications.learncodeonline.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.applications.learncodeonline.CourseActivity;
import com.akash.applications.learncodeonline.Model.ThumbnailModel;
import com.akash.applications.learncodeonline.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by pratidhi on 14/6/18.
 */

public class FreeCourseAdapter extends RecyclerView.Adapter<FreeCourseAdapter.CustomViewHolder> {
    List<ThumbnailModel> list = Collections.emptyList();
    Context context;

    public FreeCourseAdapter(List<ThumbnailModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public FreeCourseAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_layout, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FreeCourseAdapter.CustomViewHolder holder, final int position) {
        Log.i("##akx",list.get(position).getName()+"->"+list.get(position).getImageID());

//        holder.imageView.setImageResource(list.get(position).getImageID());
        holder.textView.setText(list.get(position).getName().toUpperCase());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCourse(list.get(position).getName());
            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCourse(list.get(position).getName());
            }
        });
    }

    private void launchCourse(String name) {
        if(name.equalsIgnoreCase("DATA STRUCTURE"))
        {
            Intent i = new Intent(context, CourseActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        else
        {
            Toast.makeText(context,"This is demo app. Only Data Structure course is available.",Toast.LENGTH_LONG).show();
        }
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
