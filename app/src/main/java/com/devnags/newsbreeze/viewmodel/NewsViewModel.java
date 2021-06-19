package com.devnags.newsbreeze.viewmodel;

import android.app.Application;
import android.widget.Toast;

import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.SavedArticle;
import com.devnags.newsbreeze.network.ResponseCallback;
import com.devnags.newsbreeze.repository.NewsRepo;
import com.devnags.newsbreeze.repository.SavedNewsRepo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kotlin.UShortArray;

public class NewsViewModel extends AndroidViewModel {

    NewsRepo repo;
    SavedNewsRepo savedNewsRepo;


    public NewsViewModel(@NonNull @NotNull Application application) {
        super(application);
        repo = new NewsRepo(getApplication());
        savedNewsRepo = new SavedNewsRepo(getApplication());
    }

//    MutableLiveData<Article> mutableArticles = new MutableLiveData<>();


    public LiveData<List<Article>> getArticles(){
        return repo.getArticles();
    }

    public void getAllDataFromApi(ResponseCallback callback){

        repo.getAllData(callback);

    }

    public void insertNews(List<Article> list){

        repo.loadNews(list);

    }

    public LiveData<List<Article>> getSavedArticles(){
        return savedNewsRepo.getArticles();
    }

    public void insertSavedNews(int id){
        Toast.makeText(getApplication(),"Article Saved",Toast.LENGTH_SHORT).show();
        savedNewsRepo.save(id);

    }

    public void getDetail(final int articleId){
        repo.getDetail(articleId);

    }
//    public void setMutableArticles(Article article){
//        mutableArticles.setValue(article);
//    }
//
//    public LiveData<Article> getMutableArticles(){
//        return mutableArticles;
//    }

    MutableLiveData<Article> mutableLiveData = new MutableLiveData<>();

    public void setNewsDetail(Article article){
        mutableLiveData.setValue(article);
    }

    public LiveData<Article> getNewsDetail(){

        return mutableLiveData;
    }
}

