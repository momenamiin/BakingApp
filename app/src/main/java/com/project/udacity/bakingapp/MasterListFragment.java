package com.project.udacity.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MasterListFragment extends Fragment {

    private static ArrayList<String> mSteps ;
    private static ArrayList<String> mIngrediences ;
    private static String recepeName ;
    private OnListFragmentInteractionListener mListener;

    public MasterListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mIngrediences = savedInstanceState.getStringArrayList("mIngrediences");
            mSteps = savedInstanceState.getStringArrayList("mSteps");

        }
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        TextView textView = view.findViewById(R.id.ingredient);
        for (int i = 0 ; i < mIngrediences.size() ; i ++){
            textView.append(mIngrediences.get(i) + "\n");
        }
        MasterlistAdapter adapter = new MasterlistAdapter(mSteps , mListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        Intent intent = new Intent(getContext(), BakingappWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(getActivity().getApplication())
                .getAppWidgetIds(new ComponentName(getActivity().getApplication(), BakingappWidgetProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        intent.putStringArrayListExtra("Ingrediences" , mIngrediences);
        intent.putExtra("recepeName" , recepeName);
        getActivity().sendBroadcast(intent);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(int position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("mSteps" , mSteps);
        outState.putStringArrayList("mIngrediences" , mIngrediences);

    }

    public void setSteps(ArrayList<String> Steps) {
            mSteps = Steps;
    }
    public void setingrediences(ArrayList<String> Ingrediences) {
        mIngrediences = Ingrediences;
    }
    public void setRecepeName(String RecepeName) {recepeName = RecepeName;}

}
