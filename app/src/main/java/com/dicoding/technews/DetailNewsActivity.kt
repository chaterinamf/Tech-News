package com.dicoding.technews

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import coil.load
import com.dicoding.technews.Constants.AUTHOR
import com.dicoding.technews.Constants.CONTENT
import com.dicoding.technews.Constants.PUBLISHED_TIME
import com.dicoding.technews.Constants.THUMBNAIL
import com.dicoding.technews.Constants.TITLE
import com.dicoding.technews.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        initMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun initMenu(id: Int) {
        when (id) {
            R.id.action_share -> {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    action = Intent.ACTION_SEND
                    putExtra(CONTENT, intent.getStringExtra(CONTENT))
                    type = "text/html"
                }
                startActivity(Intent.createChooser(intent, null))
            }
        }
    }

    private fun initView() {
        binding.apply {
            with(intent) {
                Log.d("DetailData", "${getStringExtra(TITLE)}")
                sivThumbnail.load(getIntExtra(THUMBNAIL, 0))
                tvNewsTitle.text = getStringExtra(TITLE)
                tvNewsContent.text =
                    HtmlCompat.fromHtml(getStringExtra(CONTENT) ?: "", FROM_HTML_MODE_LEGACY)
                tvNewsAuthor.text = getString(R.string.author, getStringExtra(AUTHOR))
                tvNewsPublishedTime.text = getString(
                    R.string.published_time, getStringExtra(
                        PUBLISHED_TIME
                    )
                )
            }
        }
    }
}