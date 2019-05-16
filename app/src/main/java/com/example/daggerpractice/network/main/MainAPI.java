package com.example.daggerpractice.network.main;

import com.example.daggerpractice.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainAPI {

    //posts?userIdd=1/
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(@Query("userId") int id);
}
