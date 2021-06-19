package com.devnags.newsbreeze.room;

import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.SavedArticle;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SavedNewsDao {

    @Query("Select Article.* from Article, SavedArticle "+
            "Where Article.id == SavedArticle.news_id " +
            "ORDER BY SavedArticle.time_saved"
    )
    LiveData<List<Article>> getArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(SavedArticle articles);


}
