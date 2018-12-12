package com.heinqi.curry_base.widget.banner;

import android.content.Context;
import android.widget.ImageView;


public abstract class MyImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

}
