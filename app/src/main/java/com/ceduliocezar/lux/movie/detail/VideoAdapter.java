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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView trailerImage;

        public ViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.video_title);
            trailerImage = (ImageView) view.findViewById(R.id.video_image);
        }
    }


    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
        this.thumbnailProvider = Injection.providesThumbnailProvider();
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.tvTitle.setText(video.getName());
        thumbnailProvider.loadThumbnail(holder.trailerImage, video, holder.tvTitle.getContext());
    }


    @Override
    public int getItemCount() {
        return videos.size();
    }
}
