
package com.devnags.newsbreeze.model;

import java.sql.Timestamp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Article.class,
        parentColumns = "id",
        childColumns = "news_id"
        ),
        indices = {@Index(value = "news_id", unique = true)},
        tableName = "SavedArticle"

)
public class SavedArticle {

    @ColumnInfo(name = "news_id")
    private final int news_id;
    @PrimaryKey
    @ColumnInfo(name = "time_saved")
    private Timestamp timestamp;

    public SavedArticle(int news_id) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.news_id = news_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getNews_id() {
        return news_id;
    }

}
