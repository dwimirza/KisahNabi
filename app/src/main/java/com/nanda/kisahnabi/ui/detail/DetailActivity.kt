package com.nanda.kisahnabi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nanda.kisahnabi.R
import com.nanda.kisahnabi.data.KisahResponse
import com.nanda.kisahnabi.databinding.ActivityDetail2Binding
import com.nanda.kisahnabi.ui.KisahAdapter

class DetailActivity : AppCompatActivity() {
    private var _binding : ActivityDetail2Binding? = null
    private val binding get() = _binding as ActivityDetail2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getParcelableExtra<KisahResponse>(DATA)
        binding.apply {
            detailNama.text = data?.name
            detailTahun.text = data?.thnKelahiran
            detailTempat.text = data?.tmp

        }
    }

    companion object{
        const val DATA = "data"
    }
}