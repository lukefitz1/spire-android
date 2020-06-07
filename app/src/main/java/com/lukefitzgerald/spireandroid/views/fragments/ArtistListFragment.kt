package com.lukefitzgerald.spireandroid.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.Artist
import com.lukefitzgerald.spireandroid.views.models.ArtistListViewModel
import java.util.*

private const val TAG = "ArtistListFragment"

class ArtistListFragment : Fragment() {

    //Required interface for hosting activities
    interface Callbacks {
        fun onArtistSelected(artistId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var artistRecyclerView: RecyclerView
    private var adapter: ArtistAdapter? = ArtistAdapter(emptyList())

    private val artistListViewModel: ArtistListViewModel by lazy {
        ViewModelProviders.of(this).get(ArtistListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        artistRecyclerView.adapter = adapter

//        updateUI()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artistListViewModel.artistListLiveData.observe(
            viewLifecycleOwner,
            Observer { artists ->
                artists?.let {
                    Log.i(TAG, "Got artists: ${artists.size}")
                    updateUI(artists)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_artist_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_artist -> {
                val artist = Artist()
                artist.firstName = "Aaron"
                artist.lastName = "Rodgers"
                artistListViewModel.addArtist(artist)
                callbacks?.onArtistSelected(artist.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(artists: List<Artist>) {
//        val artists = artistListViewModel.artists
        adapter = ArtistAdapter(artists)
        artistRecyclerView.adapter = adapter
    }

    private inner class ArtistHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var artist : Artist

        val artistNameTextView: TextView = itemView.findViewById(R.id.artist_name)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(artist: Artist) {
            this.artist = artist
            artistNameTextView.text = "${this.artist.firstName} ${this.artist.lastName}"
        }

        override fun onClick(v: View) {
//            Toast.makeText(context, "${this.artist.firstName} ${this.artist.lastName} pressed!", Toast.LENGTH_SHORT).show()
            callbacks?.onArtistSelected(artist.id)
        }
    }

    private inner class ArtistAdapter(var artists: List<Artist>) : RecyclerView.Adapter<ArtistHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
            val view = layoutInflater.inflate(R.layout.list_item_artist, parent, false)
            return ArtistHolder(view)
        }

        override fun getItemCount() = artists.size

        override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
            val artist = artists[position]

            holder.bind(artist)
        }
    }
}