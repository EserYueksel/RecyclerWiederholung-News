package com.example.recyclerwiederholung_news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerwiederholung_news.data.Repository
import com.example.recyclerwiederholung_news.data.model.NewsArticle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

    private val _news = MutableLiveData<List<NewsArticle>>()
    val news: LiveData<List<NewsArticle>>
        get() = _news

    private val _commentList = MutableLiveData<MutableList<String>>()
    val commentList: LiveData<MutableList<String>>
        get() = _commentList

    init {
        loadNews()
        Log.d("MainViewModel", "loading newslist")
    }

    fun loadComments(id: Int) {
        val article = _news.value?.find { it.id == id }

        if (article != null) {
            _commentList.value = article.comments
        }
    }

    fun addComment(comment: String) {
        _commentList.value?.add(comment)
        _commentList.value = _commentList.value
    }

    private fun loadNews(){
        viewModelScope.launch {
            delay(2000)
            _news.value = repository.loadNews()
        }
    }
}