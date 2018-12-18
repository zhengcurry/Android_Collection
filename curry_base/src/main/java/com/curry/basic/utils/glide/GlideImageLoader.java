package com.curry.basic.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.curry.basic.GlideApp;

import java.io.ByteArrayOutputStream;

/**
 * @author curry
 * @date 2018/5/23
 * @Desc 使用glide图片加载
 */
public class GlideImageLoader {
    private static final GlideImageLoader INSTANCE = new GlideImageLoader();

    private GlideImageLoader() {
    }

    public static final GlideImageLoader getInstance() {
        return INSTANCE;
    }

    //不设置加载中和加载失败的图片
    public void displayDirectImage(Context context, String url, ImageView imageView) {
        GlideApp
                .with(context)
                .load(url)
                .into(imageView);
    }

    //直接加载网络图片
    public void displayImage(Context context, String url, int resId, ImageView imageView) {
        GlideApp
                .with(context)
                .load(url)
                .placeholder(resId)
                .error(resId)
                .into(imageView);
    }

    //加载网络图片显示为圆角图片
    public void displayRoundImage(Context context, String url, int resId, ImageView imageView) {
        GlideApp
                .with(context)
                .load(url)
                .placeholder(resId)
                .error(resId)
                .transform(new GlideRoundTransform(context, 10))
                .into(imageView);
    }

    //加载网络图片显示为圆形图片(带白框)
    public void displayCircleImage(Context context, String url, int resId, ImageView imageView) {
        GlideApp
                .with(context)
                .load(url)
                .placeholder(resId)
                .error(resId)
//                .transform(new GlideCircleTransform(context))
                .transform(new GlideCircleTransform(context, 2, Color.WHITE))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    //加载网络图片显示为圆形图片
    public void displayCircleImage2(Context context, String url, int resId, ImageView imageView) {
        GlideApp
                .with(context)
                .load(url)
                .placeholder(resId)
                .error(resId)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }


    /**
     * 加载bitmap-圆形图片
     *
     * @param context
     * @param bitmap
     * @param
     * @param imageView
     */
    public void displayBitmapImg(Context context, Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        GlideApp
                .with(context)
                .load(bytes)
                .override(500, 800)
//                .placeholder(resId)
//                .error(resId)
//                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载bitmap-圆形图片
     *
     * @param context
     * @param bitmap
     * @param
     * @param imageView
     */
    public void displayBitmapCircleImage(Context context, Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        GlideApp
                .with(context)
                .load(bytes)
//                .placeholder(resId)
//                .error(resId)
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

}


