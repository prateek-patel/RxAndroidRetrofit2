package com.lavinapatel.rxjava.services;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lavinapatel.rxjava.models.Post;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lavina on 15-Jan-17.
 */
public class ForumService {

    private static final String FORUM_SERVER_URL = "http://jsonplaceholder.typicode.com/";
    private ForumApi mForumApi;

    public ForumService() {

        Retrofit retrofit = null;
        try {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(FORUM_SERVER_URL)
                    .client(getHeader())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mForumApi = retrofit.create(ForumApi.class);

    }

    /**
     * this will add the header to all the http requests
     *
     * @return
     */
    public OkHttpClient getHeader() {

        OkHttpClient okHttpClient = null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            builder.networkInterceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.addHeader("Accept", "application/json");
                    return chain.proceed(requestBuilder.build());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        okHttpClient = builder.build();

        return okHttpClient;

    }

    public ForumApi getApi() {
        return mForumApi;
    }

    public interface ForumApi {

        @GET("posts")
        public Observable<List<Post>>
        getPosts();

        @GET("posts/{id}")
        public Observable<Post>
        getPost(@Path("id") int postId);

        /*@GET("comments")
        public Observable<List<Comment>>
        getComments(@Query("postId") int postId);*/

        @POST("posts")
        public Observable<Post>
        postPost(Post post);
    }
}
