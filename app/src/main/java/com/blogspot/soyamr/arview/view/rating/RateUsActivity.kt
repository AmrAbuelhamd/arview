package com.blogspot.soyamr.arview.view.rating

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blogspot.soyamr.arview.R
import com.blogspot.soyamr.arview.databinding.ActivityRateUsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RateUsActivity : AppCompatActivity() {


    private val viewModel: RateUsViewModel by viewModels()
    private lateinit var binding: ActivityRateUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_rate_us
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}