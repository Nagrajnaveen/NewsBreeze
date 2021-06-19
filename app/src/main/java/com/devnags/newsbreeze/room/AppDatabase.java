package com.devnags.newsbreeze.room;

import android.content.Context;

import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.SavedArticle;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Article.class, SavedArticle.class}, version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "News_DB";
    public static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract NewsDao getNewsDao();
    public abstract SavedNewsDao getSavedNewsDao();

    public static AppDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            synchronized (LOCK)
            {
                instance = Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }

}
