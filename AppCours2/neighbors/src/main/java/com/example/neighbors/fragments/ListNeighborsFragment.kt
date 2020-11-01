package com.example.neighbors.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.adapters.ListNeighborHandler
import com.example.neighbors.adapters.ListNeighborsAdapter
import com.example.neighbors.data.NeighborRepository
import com.example.neighbors.models.Neighbor

class ListNeighborsFragment: Fragment(), ListNeighborHandler{

    private lateinit var recyclerView: RecyclerView




    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )

        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors,this)
        val addNeighbor: View = view.findViewById(R.id.btn_add)


        recyclerView.adapter = adapter

        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        }

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.liste_des_voisins)
        }
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        AlertDialog.Builder(context)
                .setTitle(R.string.deletedialogtitle)
                .setMessage(R.string.deletedialogmessage)
                .setPositiveButton(android.R.string.yes,
                        DialogInterface.OnClickListener { dialog, which ->
                            NeighborRepository.getInstance().deleteNeighbours(neighbor)
                            recyclerView.adapter?.notifyDataSetChanged()
                        }) // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
    }



}
