package com.ceduliocezar.lux.presentation.movie.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.backdrop.Backdrop;
import com.ceduliocezar.lux.data.backdrop.BackdropImageProvider;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 27/11/16.
 */

public class BackdropAdapter extends RecyclerView.Adapter<BackdropAdapter.ViewHolder> {

    private List<Backdrop> backdrops;
    private BackdropImageProvider backdropImageProvider;
    private BackdropAdapterListener listener;

    public BackdropAdapter(@NonNull List<Backdrop> backdrops,
                           @NonNull BackdropAdapterListener listener,
                           @NonNull BackdropImageProvider backdropImageProvider) {

        this.backdrops = checkNotNull(backdrops, "backdrops can not be null");
        this.backdropImageProvider = checkNotNull(backdropImageProvider, "backdrop image provider can not be null");
        this.listener = checkNotNull(listener, "listener can not be null");
    }

    @Override
    public BackdropAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.backdrop_item, parent, false);

        BackdropAdapter.ViewHolder vh = new BackdropAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Backdrop backdrop = backdrops.get(position);

        backdropImageProvider.load(holder.imageView, backdrop, holder.imageView.getContext());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickBackdrop(backdrop);
            }
        });

    }

    @Override
    public int getItemCount() {
        return backdrops.size();
    }

    public interface BackdropAdapterListener {
        void onClickBackdrop(Backdrop backdrop);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.imageView = (ImageView) view.findViewById(R.id.backdrop_image);

        }
    }
}