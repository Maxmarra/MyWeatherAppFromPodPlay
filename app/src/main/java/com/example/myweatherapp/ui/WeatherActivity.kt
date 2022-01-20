package com.example.myweatherapp.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.myweatherapp.R
import com.example.myweatherapp.repository.WeatherRepo
import com.example.myweatherapp.service.WeatherService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
// 1
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
// 2
        val searchMenuItem = menu.findItem(R.id.search_item)
        val searchView = searchMenuItem?.actionView as SearchView
// 3
        val searchManager = getSystemService(Context.SEARCH_SERVICE)
                as SearchManager
// 4
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )
        return true
    }

    private fun performSearch(term: String) {
        val weatherService = WeatherService.instance
        val weatherRepo = WeatherRepo(weatherService)
        GlobalScope.launch {
            val results = weatherRepo.searchWeatherRepo(term)
            Log.i(TAG, "Results = ${results.body()}")
        }
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY) ?:
            return
            performSearch(query)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }
}