package com.ceduliocezar.lux.presentation.movie.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.video.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 25/11/16.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video> videos;
    private ThumbnailProvider thumbnailProvider;
    private VideoAdapterListener listener;

    public VideoAdapter(@NonNull List<Video> videos,
                        @NonNull VideoAdapterListener listener,
                        @NonNull ThumbnailProvider thumbnailProvider) {
        this.videos = checkNotNull(videos, "videos can not be null");
        this.thumbnailProvider = checkNotNull(thumbnailProvider, "thumnail provider can not be null");
        this.listener = checkNotNull(listener, "listener can not be null");
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Video video = videos.get(position);
        holder.tvTitle.setText(video.getName());
        thumbnailProvider.loadThumbnail(holder.trailerImage, video, holder.tvTitle.getContext());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickVideo(videos.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public interface VideoAdapterListener {
        void onClickVideo(Video video);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.video_title)
        TextView tvTitle;

        @BindView(R.id.video_image)
        ImageView trailerImage;

        View view;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}
