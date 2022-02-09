package com.example.nytimes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nytimes.databinding.FragmentNewsHomeBinding
import com.example.nytimes.domain.NetworkResult
import com.example.nytimes.domain.NewsAdapter
import com.example.nytimes.domain.usecases.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_home.*

@AndroidEntryPoint
class NewsHomeFragment : Fragment() {


    val newsAdapter = NewsAdapter()
    lateinit var  newsViewModel : NewsViewModel
    lateinit var binding : FragmentNewsHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsHomeBinding.inflate(inflater)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.rvList.adapter = newsAdapter

        binding.refreshlayout.setOnRefreshListener {
            getAllNewsData()

            refreshlayout.isRefreshing = false
        }

        readFromDatabase()

        return binding.root
    }

    private fun readFromDatabase() {
        newsViewModel.readDatabaseUseCase.observeOnce(viewLifecycleOwner, Observer { database ->
            if (database.isNotEmpty()){
                newsAdapter.submitList(database[0].data.results)
            }else{
                getAllNewsData()
                hideShimmer()
            }
        })

    }

    private fun getAllNewsData() {
        newsViewModel.getTime()
        newsViewModel.getNyTime.observe(viewLifecycleOwner, Observer { response ->
            when(response){

                is NetworkResult.Sucess ->{
                    newsAdapter.submitList(response.data!!.results)
                    hideShimmer()
                }

                is NetworkResult.Error ->{
                    response.message
                    Toast.makeText(requireContext(), "An Error Occured", Toast.LENGTH_SHORT).show()
                    hideShimmer()
                }
                is NetworkResult.Loading ->{
                    showShimmerEffect()

                }
            }

        })
    }

    private fun showShimmerEffect(){
        binding.rvList.showShimmer()

    }

    private fun hideShimmer(){
        binding.rvList.hideShimmer()
    }
    }