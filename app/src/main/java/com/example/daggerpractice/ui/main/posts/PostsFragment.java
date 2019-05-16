package com.example.daggerpractice.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerpractice.R;
import com.example.daggerpractice.models.Post;
import com.example.daggerpractice.utils.VerticalSpacingItemDecoration;
import com.example.daggerpractice.viewmodels.VMProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";

    private PostsViewModel postsViewModel;
    private RecyclerView recyclerView;

    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;

    @Inject
    VMProviderFactory vmProviderFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       recyclerView = view.findViewById(R.id.recycler_view);
       postsViewModel = ViewModelProviders.of(this, vmProviderFactory)
               .get(PostsViewModel.class);
       initRecyclerView();
       subscribeObserver();

    }

    private void subscribeObserver(){
        postsViewModel.observePosts().removeObservers(getViewLifecycleOwner());
        postsViewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<PostResource<List<Post>>>() {
            @Override
            public void onChanged(PostResource<List<Post>> listPostResource) {
                if(listPostResource != null){
                    Log.d(TAG, "onChanged: " + listPostResource.data);
                    switch (listPostResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: Loading....");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "onChanged: Posts....");
                            postsRecyclerAdapter.setPosts(listPostResource.data);
                            break;
                        case ERROR:
                            Log.e(TAG, "onChanged: Error..." + listPostResource.message );
                            break;
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration verticalSpacingItemDecoration =
                new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(verticalSpacingItemDecoration);
        recyclerView.setAdapter(postsRecyclerAdapter);
    }
}
