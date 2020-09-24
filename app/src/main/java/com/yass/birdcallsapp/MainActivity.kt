package com.yass.birdcallsapp

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yass.birdcallsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val birdListAdapter: BirdListAdapter by lazy {
        BirdListAdapter(createBirdList())
    }

    private lateinit var binding: ActivityMainBinding

    private val mediaPlayers: MutableList<MediaPlayer> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpRecyclerView()

        setUpMediaPlayer()

        setClickListener()
    }

    override fun onPause() {
        super.onPause()

        mediaPlayers.forEach {
            it.apply {
                stop()
                reset()
                release()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayers.forEach {
            it.apply {
                stop()
                reset()
                release()
            }
        }
    }

    private fun setUpRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
            adapter = this@MainActivity.birdListAdapter
        }
    }

    private fun setClickListener() {

        this.birdListAdapter.setOnBirdImageClickListener { bird, position ->
            clickBird(bird = bird, position = position)
        }
    }

    private fun setUpMediaPlayer() {

        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    private fun createBirdList(): MutableList<Bird> {

        val birds = mutableListOf<Bird>()

        BirdNameAndVoice.values().forEach {

            val bird = Bird(
                name = getString(it.birdName),
                voice = it.voice,
                playState = PlayStatus.OFF
            )

            birds.add(bird)
        }

        return birds
    }

    private fun clickBird(bird: Bird, position: Int) {

        val mediaPlayer = MediaPlayer.create(this, bird.voice).apply {

            setOnCompletionListener { mediaPlayer ->
                birdListAdapter.notifyItemChanged(position, PlayStatus.OFF)
                stopMediaPlayer(mediaPlayer)
            }
        }

        mediaPlayers.add(mediaPlayer)

        if (bird.playState == PlayStatus.ON) {

            birdListAdapter.notifyItemChanged(position, PlayStatus.OFF)

            stopMediaPlayer(mediaPlayer)

            return
        }

        mediaPlayer.start()

        birdListAdapter.notifyItemChanged(position, PlayStatus.ON)
    }

    private fun stopMediaPlayer(mediaPlayer: MediaPlayer) {

        mediaPlayer.apply {
            stop()
            reset()
            release()
        }
    }
}
