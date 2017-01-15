package com.lavinapatel.rxjava.views;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lavinapatel.rxjava.R;
import com.lavinapatel.rxjava.adapters.PostsAdapter;
import com.lavinapatel.rxjava.models.Post;
import com.lavinapatel.rxjava.presenters.ListPresenter;
import com.lavinapatel.rxjava.services.ForumService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listViewPosts)
    ListView mListViewPosts;

    PostsAdapter mPostsAdapter;

    ListPresenter mListPresenter;
    ForumService mForumService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ButterKnife.bind(this);
        mListViewPosts = (ListView) findViewById(R.id.listViewPosts);

//        mListViewPosts.setOnClickListener(this);
        mListViewPosts.setOnItemClickListener(this);

        ArrayList<Post> dummyPosts = new ArrayList<Post>();
        mPostsAdapter = new PostsAdapter(this, dummyPosts);
        mListViewPosts.setAdapter(mPostsAdapter);

        mForumService = new ForumService();
        mListPresenter = new ListPresenter(this, mForumService);
        mListPresenter.loadPosts();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Post p = mPostsAdapter.getItem(position);
        int postId = p.id;

        Toast.makeText(this, "Post id: " + postId, Toast.LENGTH_LONG).show();

        /*Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("postId", postId);
        startActivity(detailIntent);*/
    }

    public void displayPosts(List<Post> posts) {

        mPostsAdapter.clear();
        mPostsAdapter.addAll(posts);
        mPostsAdapter.notifyDataSetInvalidated();
    }
}
