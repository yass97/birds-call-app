package com.yass.birdcallsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yass.birdcallsapp.databinding.ItemBirdBinding

typealias OnBirdImageClickListener = (bird: Bird, position: Int) -> Unit

class BirdListAdapter(
    private val birds: MutableList<Bird>
) : RecyclerView.Adapter<BirdListAdapter.ViewHolder>() {

    private lateinit var onBirdImageClickListener: OnBirdImageClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemBirdBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {

        if (payloads.any()) {

            (payloads.first() as? PlayStatus)?.let {
                birds[position].playState = it
            }
        }

        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val bird = birds[position]

        holder.binding.apply {

            this.bird = bird

            root.setOnClickListener {
                onBirdImageClickListener.invoke(bird, position)
            }
        }
    }

    override fun getItemCount(): Int = birds.size

    fun setOnBirdImageClickListener(listener: OnBirdImageClickListener) {
        this.onBirdImageClickListener = listener
    }

    class ViewHolder(val binding: ItemBirdBinding) : RecyclerView.ViewHolder(binding.root)
}