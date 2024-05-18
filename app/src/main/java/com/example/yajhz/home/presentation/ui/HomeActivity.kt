package com.example.yajhz.home.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.yajhz.R
import com.example.yajhz.databinding.ActivityHomeBinding
import com.example.yajhz.home.presentation.adapter.BaseCategoryAdapter
import com.example.yajhz.home.presentation.adapter.PopularAdapter
import com.example.yajhz.home.presentation.adapter.TrendingAdapter
import com.example.yajhz.home.presentation.viewmodel.HomeViewModel
import com.example.yajhz.login.presentation.ui.LoginActivity
import com.example.yajhz.shared.local.PrefsHelper
import com.example.yajhz.shared.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    @Inject
    lateinit var prefsHelper:PrefsHelper
    private val homeViewMode:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUserData()
        initRecyclerView()
        getBaseCategory()
        observeBaseCategory()
        getPopular()
        observePopular()
        getTrending()
        observeTrending()
    }



    @SuppressLint("SetTextI18n")
    private fun updateUserData(){
        prefsHelper.getUserData()?.let { userData->
            binding.apply {
                tvName.text = userData.name
                if (userData.addresses.isNotEmpty()){
                    val addresses = userData.addresses[0]
                    if (addresses.address!=null){
                        tvAddress.text = addresses.address+" ( "+addresses.street+"-"+addresses.name
                    }
                    else{
                        tvAddress.text = addresses.lat+" ,"+addresses.lng

                    }
                }
            }
        }
    }
    private fun getBaseCategory(){
        homeViewMode.getBaseCategory("en")
    }
    private fun observeBaseCategory(){
        homeViewMode.baseCategoryResult.observe(this@HomeActivity){resource->
            when (resource) {
                is Resource.Loading -> {
                    binding.baseCategoryProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    // Handle successful signup
                    binding.baseCategoryProgressBar.visibility = View.GONE
                    resource.data?.data?.let { getBaseCategoryAdapter()?.updateData(it.data) }
                }
                is Resource.Error -> {
                    binding.baseCategoryProgressBar.visibility = View.GONE
                    // Show error message
                    Toast.makeText(this@HomeActivity, resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun getPopular(){
        homeViewMode.getPopular("en",29.1931,30.6421,1)
    }
    private fun observePopular(){
        homeViewMode.popularResult.observe(this@HomeActivity){resource->
            when (resource) {
                is Resource.Loading -> {
                    binding.popularProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    // Handle successful signup
                    binding.popularProgressBar.visibility = View.GONE
                    resource.data?.data?.let { getPopularAdapter()?.updateData(it) }
                }
                is Resource.Error -> {
                    binding.popularProgressBar.visibility = View.GONE
                    // Show error message
                    Toast.makeText(this@HomeActivity, resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getTrending(){
        homeViewMode.getTrending("en",29.1931,30.6421,1)
    }
    private fun observeTrending(){
        homeViewMode.trendingResult.observe(this@HomeActivity){resource->
            when (resource) {
                is Resource.Loading -> {
                    binding.trendingProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    // Handle successful signup
                    binding.trendingProgressBar.visibility = View.GONE
                    resource.data?.data?.let { getTrendingAdapter()?.updateData(it) }
                }
                is Resource.Error -> {
                    binding.trendingProgressBar.visibility = View.GONE
                    // Show error message
                    Toast.makeText(this@HomeActivity, resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun initRecyclerView() {
        binding.apply {
            rvCategories.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL, false)
            rvCategories.adapter = BaseCategoryAdapter(ArrayList())

            rvPopularNow.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL, false)
            rvPopularNow.adapter = PopularAdapter(ArrayList())

            rvTrendingNow.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL, false)
            rvTrendingNow.adapter = TrendingAdapter(ArrayList())
        }
    }
    private fun getBaseCategoryAdapter() =
        binding.rvCategories.adapter as? BaseCategoryAdapter
    private fun getPopularAdapter() =
        binding.rvPopularNow.adapter as? PopularAdapter

    private fun getTrendingAdapter() =
        binding.rvTrendingNow.adapter as? TrendingAdapter


}