package com.example.toptracks.View;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class ImageUtils {
    public static void loadImage(ImageView imageView, Bitmap bitmap) {
        Glide.with(imageView.getContext()).load(bitmap).transform(new CircleCrop()).into(imageView);
    }
}
