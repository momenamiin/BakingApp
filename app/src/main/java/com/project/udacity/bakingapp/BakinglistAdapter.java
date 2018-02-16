package com.project.udacity.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashMap;

/**
 * Created by momenamiin on 2/11/18.
 */

public class BakinglistAdapter extends RecyclerView.Adapter<BakinglistAdapter.BakinglistAdapterViewHolder> {

    private HashMap<String,String> mBakingList ;
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
        String Name = (String) mBakingList.keySet().toArray()[position];
        String serving = mBakingList.get(Name);
        holder.nametextView.setText(Name);
        holder.servingtextView.setText("Serving : " + serving);
    }

    @Override
    public int getItemCount() {
        if (mBakingList == null){
            return 0 ;
        }else{
            return mBakingList.size();

        }
    }

    public class BakinglistAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView nametextView ;
        public final TextView servingtextView ;

        public BakinglistAdapterViewHolder(View itemView) {
            super(itemView);
            nametextView = itemView.findViewById(R.id.recipeName);
            servingtextView = itemView.findViewById(R.id.servingnum);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
        int adapterpostion = getAdapterPosition();
        mClickHandler.onclick(adapterpostion);
        }
    }
    public void setBakingList(HashMap<String , String> Bakinglist){
        mBakingList = Bakinglist ;
        notifyDataSetChanged();
    }
}
