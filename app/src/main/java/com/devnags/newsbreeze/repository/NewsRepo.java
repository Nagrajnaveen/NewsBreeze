package com.devnags.newsbreeze.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.ResponseData;
import com.devnags.newsbreeze.model.SavedArticle;
import com.devnags.newsbreeze.network.ApiClient;
import com.devnags.newsbreeze.network.ApiInterface;
import com.devnags.newsbreeze.network.ResponseCallback;
import com.devnags.newsbreeze.room.AppDatabase;
import com.devnags.newsbreeze.room.NewsDao;
import com.devnags.newsbreeze.room.SavedNewsDao;
import com.devnags.newsbreeze.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {

    private AppDatabase db;
    private NewsDao dao;
    private SavedNewsDao savedNewsDao;
    private LiveData<List<Article>> articles;
    private AppExecutors mExecutors;

    private static final String TAG = "NewsRepo";

    public  NewsRepo(Application application){
        db = AppDatabase.getInstance(application);
        dao = db.getNewsDao();
        savedNewsDao = db.getSavedNewsDao();
        mExecutors = AppExecutors.getInstance();

    }

    private MutableLiveData<List<Article>> mutableArticleList;

    public LiveData<List<Article>> getArticles(){
       return dao.getArticles();
    }


    ApiInterface service = ApiClient.getClient().create(ApiInterface.class);


    public void getAllData(ResponseCallback data){

        Call<ResponseData> call =service.getData();
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {



//                if (response.body() != null) {
                    Log.d(TAG, "onResponse: "+call.request().url());
                Log.d(TAG, "onResponse: "+response.body().getArticles().get(0).getTitle());
                    data.onResponse(response.body().getArticles());
//                }


            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                Log.d(TAG, "onFailure: "+t);
            }
        });

    }


    public void loadNews(List<Article> list){

        new LoadDB(db).execute(list);

    }


    private class LoadDB extends AsyncTask<List<Article>, Void ,Void>{


        public LoadDB(AppDatabase db) {
            dao= db.getNewsDao();
        }

        @Override
        protected Void doInBackground(List<Article>... lists) {

            dao.insertNews(lists[0]);

            return null;
        }
    }


    public void getDetail(final int articleId){
        mExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                dao.getArticleDetail(articleId);
                Log.d(TAG, "run: "+ dao.getArticleDetail(articleId).get(0).getContent());
            }
        });


    }




}
