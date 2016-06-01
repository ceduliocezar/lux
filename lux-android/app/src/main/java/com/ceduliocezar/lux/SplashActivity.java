package com.ceduliocezar.lux;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        GridView gridView = (GridView) findViewById(R.id.movie_grid);
        gridView.setAdapter(new Adapter(this));
    }


    private class Adapter extends BaseAdapter {

        private final LayoutInflater inflater;

        public Adapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.movie_item, null);
            }

            if (position % 2 == 0) {
                ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);

                imageView.setImageResource(R.mipmap.img1);
            }else{
                ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);

                imageView.setImageResource(R.mipmap.img2);
            }

            return convertView;
        }
    }
}
