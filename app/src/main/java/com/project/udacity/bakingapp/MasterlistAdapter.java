package com.project.udacity.bakingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.project.udacity.bakingapp.MasterListFragment.OnListFragmentInteractionListener;
import java.util.ArrayList;


public class MasterlistAdapter extends RecyclerView.Adapter<MasterlistAdapter.ViewHolder> {

    private ArrayList<String> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MasterlistAdapter(ArrayList<String> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position));

    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mIdView;
        public ViewHolder(View view) {
            super(view);
            mIdView =  view.findViewById(R.id.stepdescription);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterpostion = getAdapterPosition();
            mListener.onListFragmentInteraction(adapterpostion);
        }
    }

    public void setmValues(ArrayList<String> mValues) {
        this.mValues = mValues;
        notifyDataSetChanged();
    }
}
