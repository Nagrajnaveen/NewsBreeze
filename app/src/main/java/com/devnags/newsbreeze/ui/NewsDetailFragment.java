package com.devnags.newsbreeze.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devnags.newsbreeze.R;
import com.devnags.newsbreeze.databinding.ContentBinding;
import com.devnags.newsbreeze.databinding.FragmentNewsDetailBinding;
import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.viewmodel.NewsViewModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class NewsDetailFragment extends Fragment {

    private int mSafeArgs;
    private FragmentNewsDetailBinding binding;
    private NewsViewModel viewModel;
    private static final String TAG = "NewsDetailFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailBinding.inflate(getLayoutInflater(),container,false);

        assert getArguments() != null;
        mSafeArgs =getArguments().getInt("article_id");
        Log.d(TAG, "onCreateView: "+mSafeArgs);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        binding.setNewsViewModel(viewModel);
        binding.content.view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Article Saved",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void saveArticle(Article article){
        viewModel.insertSavedNews(article.getId());

    }
}