package com.heinqi.curry_base.http;

import com.heinqi.curry_base.base.BaseApplication;
import com.heinqi.curry_base.utils.GlobalSharedPreferences;
import com.heinqi.curry_base.utils.LogUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {
    private static final int DEFAULT_TIMEOUT = 10;

    public static final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };

    /**
     * 拦截器,添加头部
     *
     * @return
     */
    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)        //设置超时时间
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
                /*//添加日志拦截器
                .addInterceptor(new HttpLoggingInterceptor(message -> {
                    try {
                        LogUtils.e(URLDecoder.decode(message, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        LogUtils.e(message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))*/
                //添加日志拦截器
                .addInterceptor(new LogInterceptor(message -> LogUtils.e(message))
                        .setLevel(LogInterceptor.Level.BODY))
                //添加Header拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("AccessToken", GlobalSharedPreferences
                                        .getInstance()
                                        .getString("TOKEN", ""))//TOKEN
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .sslSocketFactory(new SSLSocketFactoryCompat(trustAllCert), trustAllCert)
                .build();

        return httpClient;
    }
}
