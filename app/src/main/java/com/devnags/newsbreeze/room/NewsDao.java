package com.devnags.newsbreeze.room;

import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.SavedArticle;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NewsDao {

    @Query("Select * from Article")
    LiveData<List<Article>> getArticles();

    @Insert
    void insertNews(List<Article> articles);

    @Query("Select * from Article Where id =:id")
     List<Article> getArticleDetail(int id);





}
