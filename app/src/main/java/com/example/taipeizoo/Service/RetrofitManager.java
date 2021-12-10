package com.example.taipeizoo.Service;

import android.util.Log;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    //Singleton
    private static volatile RetrofitManager mRetrofitManager;

    public RetrofitManager() {
        Retrofit mRetrofit = retrofit();
    }


    public <T> T createService(final Class<T> service) {
        return retrofit().create(service);
    }

    public static RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (Retrofit.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://data.taipei")  //url = baseurl + @GER("...")
                .addConverterFactory(GsonConverterFactory.create())
                .client(Objects.requireNonNull(okHttpClient()))
                .build();


    }


    private OkHttpClient okHttpClient() {
        try {
            return new OkHttpClient.Builder()
                    .addNetworkInterceptor(chain -> chain.proceed(chain.request()))
                    .retryOnConnectionFailure(true)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .build();

        } catch (Exception e) {
            Log.v("OkhttpException", "Exception" + e);
        }
        return null;
    }


    /**
     * 设置https 访问的时候对所有证书都进行信任
     *
     * @throws Exception
     */
    public OkHttpClient getSSLOkHttpClient() throws Exception {
        final X509TrustManager trustManager = new X509TrustManager() {

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
    }


}
