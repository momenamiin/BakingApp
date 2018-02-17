package com.project.udacity.bakingapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by momenamiin on 2/11/18.
 */

public class BakinglistAdapter extends RecyclerView.Adapter<BakinglistAdapter.BakinglistAdapterViewHolder> {

    private ArrayList<String> mImageUrls ;
    private ArrayList<String> mBakinglistNames ;
    private ArrayList<String> mBakinglistServing ;
    private final BakinglistAdapterOnclickHandler mClickHandler ;

    public BakinglistAdapter(BakinglistAdapterOnclickHandler Clickhandler){
        mClickHandler = Clickhandler;
    }

    public interface BakinglistAdapterOnclickHandler{
        void onclick(int position);
    }
    Context context ;

    @Override
    public BakinglistAdapter.BakinglistAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int itemid = R.layout.recipecard;
        boolean shouldAttachToParentImmediately = false;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(itemid, parent, shouldAttachToParentImmediately);
        return new BakinglistAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BakinglistAdapter.BakinglistAdapterViewHolder holder, int position) {

        String Name = mBakinglistNames.get(position);
        String ImageUrl = mImageUrls.get(position);
        if (ImageUrl.length() != 0){
            Picasso.with(context).load(ImageUrl).into(holder.imageView);
        }else {
            holder.imageView.setVisibility(View.GONE);
        }
        String serving = mBakinglistServing.get(position);
        holder.nametextView.setText(Name);
        holder.servingtextView.setText("Serving : " + serving);
    }

    @Override
    public int getItemCount() {
        if (mBakinglistNames == null){
            return 0 ;
        }else{
            return mBakinglistNames.size();

        }
    }

    public class BakinglistAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView nametextView ;
        public final TextView servingtextView ;
        public final ImageView imageView ;

        public BakinglistAdapterViewHolder(View itemView) {
            super(itemView);
            nametextView = itemView.findViewById(R.id.recipeName);
            servingtextView = itemView.findViewById(R.id.servingnum);
            imageView = itemView.findViewById(R.id.recipeImage);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
        int adapterpostion = getAdapterPosition();
        mClickHandler.onclick(adapterpostion);
        }
    }
    public void setBakingList(ArrayList<String> BakinglistNames ,ArrayList<String> BakinglistServing, ArrayList<String> ImageUrls){
        mImageUrls = ImageUrls;
        mBakinglistNames = BakinglistNames ;
        mBakinglistServing = BakinglistServing ;
        notifyDataSetChanged();
    }

}
