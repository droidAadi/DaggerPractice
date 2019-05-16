package com.example.daggerpractice.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice.SessionManager;
import com.example.daggerpractice.models.Post;
import com.example.daggerpractice.network.main.MainAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {

    private static final String TAG = "PostsViewModel";

    private final SessionManager sessionManager;
    private final MainAPI mainAPI;

    private MediatorLiveData<PostResource<List<Post>>> postsLiveData;


    @Inject
    public PostsViewModel(SessionManager sessionManager, MainAPI mainAPI) {
        Log.d(TAG, "PostsViewModel: View Model is working");
        this.sessionManager = sessionManager;
        this.mainAPI = mainAPI;
    }

    public LiveData<PostResource<List<Post>>> observePosts() {
        if (postsLiveData == null) {
            postsLiveData = new MediatorLiveData<>();
            postsLiveData.setValue(PostResource.loading((List<Post>) null));

            /**
             * You should not ever try to request an object directly from LiveData Object that is
             * in another class. You should try an observe it here instead.
             * As doing it here will make code complex, hence for now we are requesting it directly.
             * Alternate way is to create a single view model and share it across.
             */
            final LiveData<PostResource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainAPI.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId())
                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @Override
                                public List<Post> apply(Throwable throwable) throws Exception {
                                    Log.e(TAG, "apply: ", throwable);
                                    Post post = new Post();
                                    post.setId(-1);
                                    List<Post> postList = new ArrayList<>();
                                    postList.add(post);
                                    return postList;
                                }
                            })
                            .map(new Function<List<Post>, PostResource<List<Post>>>() {
                                @Override
                                public PostResource<List<Post>> apply(List<Post> posts) throws Exception {
                                    if (posts.size() >= 0) {
                                        if (posts.get(0).getId() == -1) {
                                            return PostResource.error("Something went wrong", null);
                                        }
                                    }
                                    return PostResource.success(posts);
                                }
                            })
                            .subscribeOn(Schedulers.io())
            );
            postsLiveData.addSource(source, new Observer<PostResource<List<Post>>>() {
                @Override
                public void onChanged(PostResource<List<Post>> listPostResource) {
                    postsLiveData.setValue(listPostResource);
                }
            });
        }
        return postsLiveData;
    }
}
