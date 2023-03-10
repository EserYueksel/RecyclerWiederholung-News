package com.example.recyclerwiederholung_news.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recyclerwiederholung_news.MainViewModel
import com.example.recyclerwiederholung_news.adapter.NewsAdapter
import com.example.recyclerwiederholung_news.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    // wenn sich mehrere Fragmente ein Viemodel teilen.
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val newsAdapter = NewsAdapter()
        binding.newsRecycler.adapter = newsAdapter

        viewModel.news.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", "newslist livedata received")
            newsAdapter.submitList(it)
        }
    }

}