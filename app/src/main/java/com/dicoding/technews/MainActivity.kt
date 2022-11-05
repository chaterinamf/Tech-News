package com.dicoding.technews

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.technews.Constants.AUTHOR
import com.dicoding.technews.Constants.CONTENT
import com.dicoding.technews.Constants.PUBLISHED_TIME
import com.dicoding.technews.Constants.THUMBNAIL
import com.dicoding.technews.Constants.TITLE
import com.dicoding.technews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NewsAdapter.OnItemClickCallback {
    private lateinit var binding: ActivityMainBinding
    private var data: ArrayList<News> = arrayListOf()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data.addAll(NewsData.listData)
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        initMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun initMenu(id: Int) {
        when (id) {
            R.id.about_page -> startActivity(Intent(this, AboutPageActivity::class.java))
        }
    }

    private fun initRecyclerView() = lifecycleScope.launchWhenCreated {
        binding.rvNews.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            newsAdapter = NewsAdapter(this@MainActivity)
            newsAdapter.newsItem = data
            adapter = newsAdapter
        }
    }

    private fun sendData(item: News) {
        val intent = Intent(this, DetailNewsActivity::class.java).apply {
            putExtra(TITLE, item.title)
            putExtra(THUMBNAIL, item.thumbnail)
            putExtra(CONTENT, item.content)
            putExtra(AUTHOR, item.author)
            putExtra(PUBLISHED_TIME, item.published_time)
        }
        startActivity(intent)
    }

    override fun onItemClicked(item: News) {
        sendData(item)
    }
}