package com.blogspot.soyamr.arview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.arview.model.domain.FinalGameInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class GameAdapter(private val games: ArrayList<FinalGameInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(private val viewItem: View) :
        RecyclerView.ViewHolder(viewItem) {
        fun setGameData(finalGameInfo: FinalGameInfo) {
            viewItem.viewersNumTextView.text = finalGameInfo.viewersNum.toString()
            viewItem.channelsNumTextView.text = finalGameInfo.channelsNum.toString()
            viewItem.gameNameTextView.text = finalGameInfo.name
            Picasso.get().load(finalGameInfo.coverUrl).into(viewItem.coverImageView);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).setGameData(games[position])
    }

    fun updateData(gamesData: List<FinalGameInfo>?) {
        games.clear()
        try {
            games.addAll(gamesData!!)
        } catch (e: Exception) {
        }
        notifyDataSetChanged()
    }
}
