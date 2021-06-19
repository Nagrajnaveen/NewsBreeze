package com.devnags.newsbreeze.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.SavedArticle;
import com.devnags.newsbreeze.room.AppDatabase;
import com.devnags.newsbreeze.room.SavedNewsDao;
import com.devnags.newsbreeze.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SavedNewsRepo {

    private AppDatabase db;
    private SavedNewsDao dao;
    private LiveData<List<SavedArticle>> articles;
    private static final String TAG = "NewsRepo";
    private AppExecutors mExecutors;

    public  SavedNewsRepo(Application application){
        db = AppDatabase.getInstance(application);
        dao = db.getSavedNewsDao();
        mExecutors = AppExecutors.getInstance();

    }

    public LiveData<List<Article>> getArticles(){
        return dao.getArticles();
    }



    public void save(final int articleId) {
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                SavedArticle savedArticle = new SavedArticle(articleId);
                dao.insertNews(savedArticle);
            }
        });
    }




}
