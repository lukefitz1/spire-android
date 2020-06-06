package com.lukefitzgerald.spireandroid.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.Artist
import com.lukefitzgerald.spireandroid.views.models.ArtistListViewModel

private const val TAG = "ArtistListFragment"

class ArtistListFragment : Fragment() {

    private lateinit var artistRecyclerView: RecyclerView
    private var adapter: ArtistAdapter? = null

    private val artistListViewModel: ArtistListViewModel by lazy {
        ViewModelProviders.of(this).get(ArtistListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total Artists: ${artistListViewModel.artists.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artist_list, container, false)

        artistRecyclerView = view.findViewById(R.id.artists_recycler_view) as RecyclerView
        artistRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {
        val artists = artistListViewModel.artists
        adapter = ArtistAdapter(artists)
        artistRecyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private inner class ArtistHolder(view: View) : RecyclerView.ViewHolder(view) {

        val artistNameTextView: TextView = itemView.findViewById(R.id.artist_name)
    }

    private inner class ArtistAdapter(var artists: List<Artist>) : RecyclerView.Adapter<ArtistHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
            val view = layoutInflater.inflate(R.layout.list_item_artist, parent, false)
            return ArtistHolder(view)
        }

        override fun getItemCount() = artists.size

        override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
            val artist = artists[position]
            holder.apply {
                artistNameTextView.text = "${artist.firstName} ${artist.lastName}"
            }
        }
    }
}