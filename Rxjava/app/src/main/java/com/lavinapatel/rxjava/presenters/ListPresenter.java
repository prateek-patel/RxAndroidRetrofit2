package com.lavinapatel.rxjava.presenters;

import android.app.Activity;

import com.lavinapatel.rxjava.models.Post;
import com.lavinapatel.rxjava.services.ForumService;
import com.lavinapatel.rxjava.views.ListActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lavina on 15-Jan-17.
 */
public class ListPresenter implements Observer<List<Post>>{

    ListActivity mView;
    ForumService mForum;

    public ListPresenter(Activity activity, ForumService mForumService) {
        this.mView = (ListActivity) activity;
        this.mForum = mForumService;
    }

    public void loadPosts() {

        ForumService.ForumApi api = mForum.getApi();

        Observable<List<Post>> observable = api.getPosts();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Post> value) {
        mView.displayPosts(value);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    class MyObserver implements Observer<List<Post>> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<Post> value) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
