package com.devnags.newsbreeze.network;

import com.devnags.newsbreeze.model.Article;

import java.util.List;

public interface ResponseCallback {

    void onResponse(List<Article> articles);


}
