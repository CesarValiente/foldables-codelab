package com.codelab.foldables.window_manager.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.NavHostFragment
import com.codelab.foldables.window_manager.R
import com.codelab.foldables.window_manager.databinding.ListPaneBinding

class TwoPaneFragment : AbstractListDetailFragment() {

    private lateinit var binding: ListPaneBinding

    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListPaneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDetailPaneNavHostFragment(): NavHostFragment {
        return NavHostFragment.create(R.navigation.two_pane_navigation)
    }

    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)

        binding.fragmentOne.setOnClickListener {
            openDetails(R.id.first_fragment)
        }
        binding.fragmentTwo.setOnClickListener {
            openDetails(R.id.second_fragment)
        }
        binding.fragmentThree.setOnClickListener {
            openDetails(R.id.third_fragment)
        }
    }

    private fun openDetails(destinationId: Int) {
        val detailNavController = detailPaneNavHostFragment.navController
        detailNavController.navigate(
            destinationId,
            null,
            NavOptions.Builder()
                .setPopUpTo(detailNavController.graph.startDestinationId, inclusive = true)
                .build()
        )
        slidingPaneLayout.open()
    }
}