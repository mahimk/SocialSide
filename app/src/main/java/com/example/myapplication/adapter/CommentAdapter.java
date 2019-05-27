package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.model.CommentDataModel;


import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<CommentDataModel> comment_ArrayList;
    Context mContext;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CommentAdapter(ArrayList<CommentDataModel> arrayList_commentList, Context context) {

         this.comment_ArrayList=arrayList_commentList;
         this.mContext=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;



            itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.displaycomment, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {

        final CommentDataModel comments = comment_ArrayList.get(position);

        String timestamp = convertEpochToCurrent(comments.getTimpstamp());

        if (imageLoader == null)
        imageLoader = AppController.getInstance().getImageLoader();

        holder.name.setText(comments.getName());
        holder.profilePic.setImageUrl(comments.getProfilePic(), imageLoader);
        holder.message.setText(comments.getComment());
        holder.timestamp.setText(timestamp);

    }



    @Override
    public int getItemCount() {

        return comment_ArrayList.size();

    }

    public static String convertEpochToCurrent(String ourDate)
    {
        String localTime;
        try {

            long timestamp =Long.parseLong(ourDate);
            Date d = new Date(timestamp);
            String strDateFormat = "yyyy-MM-dd hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

            localTime=dateFormat.format(d);

        }
        catch (Exception e) {
            localTime = "00-00-0000 00:00";
        }
        return  localTime;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, message, timestamp;
       // public CircularImageView profile_image;
        public NetworkImageView profilePic;


        public ViewHolder(@NonNull View view) {
            super(view);


            name = view.findViewById(R.id.name);
            message = view.findViewById(R.id.message);
            timestamp = view.findViewById(R.id.timestamp);
           // profile_image = view.findViewById(R.id.profile_image);
            profilePic = view.findViewById(R.id.profilePic);


        }
    }
}
