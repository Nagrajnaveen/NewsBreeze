package com.devnags.newsbreeze.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devnags.newsbreeze.databinding.ArticleListItemBinding;
import com.devnags.newsbreeze.databinding.SavedArticleListItemBinding;
import com.devnags.newsbreeze.model.Article;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SavedArticleListAdapter extends ListAdapter<Article, SavedArticleListAdapter.MyViewHolder> {

    SavedArticleListItemBinding binding;
    UserClickInterface itemListener;

    public SavedArticleListAdapter(UserClickInterface itemClickListener) {
        super(Article.itemCallback);
        itemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    binding = SavedArticleListItemBinding.inflate(layoutInflater,parent,false);
//    binding.setUserClickInterface(itemListener);
    return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Article article = getItem(position);

        holder.listBinding.textView7.setText(article.getPublishedAt());
        holder.listBinding.textView8.setText(article.getAuthor());
        holder.listBinding.textView5.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage()).into(holder.listBinding.imageView4);
//        holder.listBinding.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                itemListener.onItemClick(article);
//            }
//        });
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        SavedArticleListItemBinding listBinding;
        public MyViewHolder( SavedArticleListItemBinding binding) {

            super(binding.getRoot());
            this.listBinding = binding;
        }
    }


    public interface UserClickInterface{
        void onItemClick(Article user);
        void saveBtnClick(Article article);
    }


}
