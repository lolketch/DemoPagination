package com.example.testpagination.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testpagination.R
import com.example.testpagination.base.App
import com.example.testpagination.di.MultiViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {
    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory
    lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val mainAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.inject(mainFragment = this)
        viewModel = ViewModelProvider(this, multiViewModelFactory)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter.withLoadStateHeaderAndFooter(
                header = MyLoadStateAdapter(),
                footer = MyLoadStateAdapter()
            )
        }

        viewModel.characters.observe(viewLifecycleOwner, {
            mainAdapter.submitData(lifecycle = lifecycle, pagingData = it)
        })

        mainAdapter.addLoadStateListener { state ->
            recyclerView.isVisible = state.refresh != LoadState.Loading
            progressBar.isVisible = state.refresh == LoadState.Loading
        }
    }
}