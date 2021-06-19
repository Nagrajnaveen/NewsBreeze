package com.devnags.newsbreeze.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devnags.newsbreeze.R;
import com.devnags.newsbreeze.adapters.ArticleListAdapter;
import com.devnags.newsbreeze.databinding.FragmentNewsHomeBinding;
import com.devnags.newsbreeze.model.Article;
import com.devnags.newsbreeze.model.SavedArticle;
import com.devnags.newsbreeze.network.ResponseCallback;
import com.devnags.newsbreeze.viewmodel.NewsViewModel;

import java.util.List;


public class NewsHomeFragment extends Fragment implements ArticleListAdapter.UserClickInterface {

    private FragmentNewsHomeBinding mBinding;
    private NewsViewModel viewModel;
    private ArticleListAdapter adapter;
    NavController navController;
    private Bundle mBundle;
    private static final String TAG = "NewsHomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentNewsHomeBinding.inflate(getLayoutInflater(),container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        adapter = new ArticleListAdapter(this);
        mBinding.articlesListView.setAdapter(adapter);
        mBundle = new Bundle();
        viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        mBinding.navigateToSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_newsHomeFragment_to_savedArticlesFragment);
            }
        });


        viewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter.submitList(articles);
            }
        });



        viewModel.getAllDataFromApi(new ResponseCallback() {
            @Override
            public void onResponse(List<Article> articles) {
                Log.d(TAG, "onResponseCallBack: "+articles.get(0).getAuthor());
                viewModel.insertNews(articles);
            }
        });


    }

    @Override
    public void onItemClick(Article article) {
        Log.d(TAG, "onItemClick: "+article.getId());
        viewModel.setNewsDetail(article);
        mBundle.putInt("article_id",article.getId());
        navController.navigate(R.id.action_newsHomeFragment_to_newsDetailFragment,mBundle);
    }

    @Override
    public void saveBtnClick(Article article) {

        Log.d(TAG, "saveBtnClick: "+article.getDescription());

        viewModel.insertSavedNews(article.getId());
        Toast.makeText(getActivity(),"Article Saved",Toast.LENGTH_SHORT).show();

    }
}