package com.ceduliocezar.lux.movie.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceduliocezar.lux.Injection;
import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.video.Video;

import java.util.List;

/**
 * Created by cedulio on 25/11/16.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video> videos;
    private ThumbnailProvider thumbnailProvider;
    private VideoAdapterListener listener;

    public interface VideoAdapterListener {
        void onClickVideo(Video video);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView trailerImage;
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.tvTitle = (TextView) view.findViewById(R.id.video_title);
            this.trailerImage = (ImageView) view.findViewById(R.id.video_image);
        }
    }


    public VideoAdapter(List<Video> videos, VideoAdapterListener listener) {
        this.videos = videos;
        this.thumbnailProvider = Injection.providesThumbnailProvider();
        this.listener = listener;
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

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
}
