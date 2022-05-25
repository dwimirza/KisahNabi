package com.nanda.kisahnabi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nanda.kisahnabi.data.KisahResponse
import com.nanda.kisahnabi.databinding.ActivityMainBinding
import com.nanda.kisahnabi.ui.detail.DetailActivity
import com.nanda.kisahnabi.utils.OnItemClickCallback

class MainActivity : AppCompatActivity(), OnItemClickCallback {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewmodel : MainViewModel? = null
    private val viewmodel get() = _viewmodel as MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewmodel.getData()

        viewmodel.kisahResponse.observe(this){ showData(it) }
        viewmodel.isLoading.observe(this){ showLoading(it) }
        viewmodel.isError.observe(this){ showError(it) }

    }

    private fun showData(data: List<KisahResponse>?) {
        binding.recyclerMain.apply {
            val mAdapter = KisahAdapter()
            mAdapter.setData(data)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = mAdapter
            mAdapter.setOnItemClickCallback(this@MainActivity)
        }
    }


    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true){
            binding.progressMain.visibility = View.VISIBLE
            binding.recyclerMain.visibility = View.INVISIBLE
        } else {
            binding.progressMain.visibility = View.INVISIBLE
            binding.recyclerMain.visibility = View.VISIBLE
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error", )
    }

    override fun onItemClicked(item: KisahResponse) {
        startActivity(Intent(applicationContext, DetailActivity::class.java).putExtra("data", item))
    }
}