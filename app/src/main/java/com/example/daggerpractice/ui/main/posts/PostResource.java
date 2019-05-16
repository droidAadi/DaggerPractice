package com.example.daggerpractice.ui.main.posts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PostResource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public PostResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> PostResource<T> success (@Nullable T data) {
        return new PostResource<>(Status.SUCCESS, data, null);
    }

    public static <T> PostResource<T> error(@NonNull String msg, @Nullable T data) {
        return new PostResource<>(Status.ERROR, data, msg);
    }

    public static <T> PostResource<T> loading(@Nullable T data) {
        return new PostResource<>(Status.LOADING, data, null);
    }

    public enum Status { SUCCESS, ERROR, LOADING}
}