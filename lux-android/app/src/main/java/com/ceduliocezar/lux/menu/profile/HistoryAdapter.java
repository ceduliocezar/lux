package com.ceduliocezar.lux.menu.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.cloud.Genre;

import java.util.List;

/**
 * Created by cedulio on 05/06/16.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    private final List<History> historyActions;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextAction;
        public TextView mTextMovie;

        public ViewHolder(View v) {
            super(v);
            mTextAction = (TextView) v.findViewById(R.id.history_action);
            mTextMovie =  (TextView) v.findViewById(R.id.history_movie);
        }
    }

    public HistoryAdapter(List<History> historyActions) {
        this.historyActions = historyActions;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_history_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position % 2 == 0 ){
            holder.mTextAction.setText("Rated");
        }else{
            holder.mTextAction.setText("Watched");
        }

        if (position%3 == 0){
            holder.mTextMovie.setText("Star Wars: The Force Awakens");
        }else{
            holder.mTextMovie.setText("Matrix");
        }

    }

    @Override
    public int getItemCount() {
        return 100;
    }
}