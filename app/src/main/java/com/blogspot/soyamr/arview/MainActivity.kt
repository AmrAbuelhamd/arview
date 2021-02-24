package com.blogspot.soyamr.arview

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.soyamr.arview.databinding.ActivityMainBinding
import com.blogspot.soyamr.arview.model.net.FinalGameInfo
import com.blogspot.soyamr.notforgotagain.model.net.Network
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    var games = ArrayList<FinalGameInfo>()

    lateinit var myAdapter: GameAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        setObservers()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        myAdapter = GameAdapter(games)

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