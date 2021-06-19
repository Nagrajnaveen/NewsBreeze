package com.devnags.newsbreeze.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devnags.newsbreeze.R;
import com.devnags.newsbreeze.adapters.SavedArticleListAdapter;
import com.devnags.newsbreeze.databinding.FragmentSavedArticlesBinding;
import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.viewmodel.NewsViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SavedArticlesFragment extends Fragment implements SavedArticleListAdapter.UserClickInterface {

    FragmentSavedArticlesBinding binding;
    private SavedArticleListAdapter adapter;
    private NewsViewModel viewModel;
    private static final String TAG = "SavedArticlesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSavedArticlesBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SavedArticleListAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);

        viewModel.getSavedArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter.submitList(articles);
                Log.d(TAG, "onChanged: "+articles.get(0).getDescription());
            }
        });


    }

    @Override
    public void onItemClick(Article user) {

    }

    @Override
    public void saveBtnClick(Article article) {

    }
}