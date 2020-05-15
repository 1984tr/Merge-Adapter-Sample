package com.tr1984.mergeadaptersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.MergeAdapter
import com.tr1984.mergeadaptersample.adapter.*
import com.tr1984.mergeadaptersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity
            viewModel = this@MainActivity.viewModel

            recyclerView.adapter = generateAdapter()
        }
        setContentView(binding.root)

        viewModel.fetch()
    }

    private fun generateAdapter(): MergeAdapter {
        val articleHeader = HeaderAdapter(this, "Article Search Results")
        val articleAdapter = ArticleAdapter(this, viewModel)
        val articleFooter = FooterAdapter(this, "Add Article") {
            viewModel.fetchArticle()
        }

        val feedHeader = HeaderAdapter(this, "Feed Search Results")
        val feedAdapter = FeedAdapter(this, viewModel)
        val feedFooter = FooterAdapter(this, "Delete Feed") {
            viewModel.fetchFeed()
        }

        val peopleHeader = HeaderAdapter(this, "People Search Results")
        val peopleAdapter = PeopleAdapter(this, viewModel)

        return MergeAdapter(
                articleHeader, articleAdapter, articleFooter,
                feedHeader, feedAdapter, feedFooter,
                peopleHeader, peopleAdapter)
    }
}
