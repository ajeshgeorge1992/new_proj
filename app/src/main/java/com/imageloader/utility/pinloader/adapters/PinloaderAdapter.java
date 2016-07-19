package com.imageloader.utility.pinloader.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imageloader.utility.pinloader.R;
import com.imageloader.utility.pinloader.beans.Pin;
import com.koushikdutta.ion.Ion;

import java.util.List;

public class PinloaderAdapter extends RecyclerView.Adapter<PinloaderAdapter.ViewHolder> {
    private Context mContext;
    private List<Pin> pinlist;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView likes, username,category;
        public ImageView pinnedImage;

        public ViewHolder(View view) {
            super(view);
            likes = (TextView) view.findViewById(R.id.likes);
            username = (TextView) view.findViewById(R.id.username);
            category = (TextView) view.findViewById(R.id.categories);
            pinnedImage = (ImageView) view.findViewById(R.id.pinnedImage);
        }
    }
    public PinloaderAdapter(Context mContext, List<Pin> pinlist) {
        this.mContext = mContext;
        this.pinlist = pinlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pin_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Pin singlePin = pinlist.get(position);
        holder.likes.setText(String.valueOf(singlePin.getLikes()));
        holder.username.setText(singlePin.getUserName());
        holder.category.setText(singlePin.getCategories());

        // loading images using Ion library
        Ion.with(mContext)
                .load(singlePin.getImageSmall())
                .withBitmap()
                .centerCrop()
                .placeholder(R.drawable.ic_imageloading)
                .error(R.drawable.ic_error)
                .animateIn(android.R.anim.fade_in)
                .intoImageView(holder.pinnedImage);


    }
    @Override
    public int getItemCount() {
        return pinlist.size();
    }
}
