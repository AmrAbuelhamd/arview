package com.blogspot.soyamr.arview.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.soyamr.arview.R
import com.blogspot.soyamr.arview.view.rating.RateUsActivity
import com.blogspot.soyamr.arview.databinding.ActivityMainBinding
import com.blogspot.soyamr.arview.model.domain.FinalGameInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    var games = ArrayList<FinalGameInfo>()

    lateinit var myAdapter: GameRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        setObservers()

        setupRecyclerView()

        setListeners()
    }

    private fun setListeners() {
        floatingActionButton.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RateUsActivity::class.java
                )
            )
        }
    }

    private fun setupRecyclerView() {

        myAdapter = GameRecyclerAdapter(games)

        val myViewManager = LinearLayoutManager(this)
        val recyclerView = recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = myViewManager
            adapter = myAdapter
        }
    }

    private fun setObservers() {
        viewModel.games.observe(this,
            {
                myAdapter.updateData(it)
            })
    }
}