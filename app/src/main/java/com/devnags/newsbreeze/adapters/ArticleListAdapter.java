package com.devnags.newsbreeze.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devnags.newsbreeze.databinding.ArticleListItemBinding;
import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.SavedArticle;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleListAdapter extends ListAdapter<Article, ArticleListAdapter.MyViewHolder> {

    ArticleListItemBinding binding;
    UserClickInterface itemListener;

    public ArticleListAdapter(UserClickInterface itemClickListener) {
        super(Article.itemCallback);
        itemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    binding = ArticleListItemBinding.inflate(layoutInflater,parent,false);
//    binding.setUserClickInterface(itemListener);
    return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Article article = getItem(position);


        holder.listBinding.headerTextView.setText(article.getTitle());
        holder.listBinding.descTextView.setText(article.getDescription());
        holder.listBinding.textView3.setText(article.getPublishedAt());
        Picasso.get().load(article.getUrlToImage()).into(holder.listBinding.imageView2);
        holder.listBinding.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onItemClick(article);
            }
        });
        holder.listBinding.view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.saveBtnClick(article);
            }
        });
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        ArticleListItemBinding listBinding;
        public MyViewHolder( ArticleListItemBinding binding) {

            super(binding.getRoot());
            this.listBinding = binding;
        }
    }


    public interface UserClickInterface{
        void onItemClick(Article user);
        void saveBtnClick(Article article);
    }


}
