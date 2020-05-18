package com.tr1984.mergeadaptersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.MergeAdapter
import com.tr1984.mergeadaptersample.adapter.*
import com.tr1984.mergeadaptersample.adapter.horizontal.HorizontalContainerAdapter
import com.tr1984.mergeadaptersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var peopleViewModel: PeopleViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MainViewModel::class.java)
        peopleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(PeopleViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity
            viewModel = this@MainActivity.mainViewModel

            recyclerView.adapter = generateAdapter()
        }
        setContentView(binding.root)

        mainViewModel.fetch()
    }

    private fun generateAdapter(): MergeAdapter {
        val bannerHeader = HeaderAdapter(this, "Banner Search Results")
        val bannerAdapter = HorizontalContainerAdapter(this, BannerAdapter(this, mainViewModel))

        val articleHeader = HeaderAdapter(this, "Article Search Results")
        val articleAdapter = ArticleAdapter(this, mainViewModel)
        val articleFooter = FooterAdapter(this, "Add Article") {
            mainViewModel.fetchArticle()
        }

        val feedHeader = HeaderAdapter(this, "Feed Search Results")
        val feedAdapter = FeedAdapter(this, mainViewModel)
        val feedFooter = FooterAdapter(this, "Delete Feed") {
            mainViewModel.fetchFeed()
        }

        val peopleHeader = HeaderAdapter(this, "People Search Results")
        val peopleAdapter = PeopleAdapter(this, peopleViewModel)

        return MergeAdapter(
            MergeAdapter.Config.Builder().setIsolateViewTypes(false).build(),
            bannerHeader, bannerAdapter,
            articleHeader, articleAdapter, articleFooter,
            feedHeader, feedAdapter, feedFooter,
            peopleHeader, peopleAdapter
        )
    }
}
