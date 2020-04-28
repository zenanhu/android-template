package xyz.zenan.android.api;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The interceptor for rest api request.
 */
public class RestRequestInterceptor implements Interceptor {
    Context applicationContext;

    @Inject
    public RestRequestInterceptor(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
//        .addQueryParameter("key", "value") // add url params
                .build();
        Request.Builder builder = request.newBuilder()
                .url(url);
//        .addHeader("Request-Id", "request id"); // add header

        return chain.proceed(builder.build());
    }
}
