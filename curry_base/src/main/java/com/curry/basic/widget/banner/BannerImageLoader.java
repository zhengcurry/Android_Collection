package com.curry.basic.widget.banner;

import android.content.Context;
import android.widget.ImageView;

import com.curry.basic.GlideApp;

public class BannerImageLoader extends MyImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        GlideApp
                .with(context)
                .load(path)
                .centerCrop()
//                .placeholder(R.mipmap.provider_default_pic)
                .into(imageView);
    }
}
