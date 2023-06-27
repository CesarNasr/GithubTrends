package com.example.githubtrends.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubtrends.databinding.FragmentTrendingReposBinding
import com.example.githubtrends.presentation.adapters.ReposAdapter
import com.example.githubtrends.presentation.utils.ErrorType
import com.example.githubtrends.presentation.utils.UiState
import com.example.githubtrends.presentation.viewmodels.TrendingReposViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class TrendingReposFragment : Fragment() {
    private lateinit var binding: FragmentTrendingReposBinding
    private val viewModel: TrendingReposViewModel by viewModels()

    @Inject
    lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTrendingReposBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        collectUiStates()
    }


    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(activity)

        binding.reposRecyclerview.apply {
            adapter = reposAdapter
            layoutManager = mLayoutManager
        }

        val dividerItemDecoration = DividerItemDecoration(
            binding.reposRecyclerview.context,
            mLayoutManager.orientation
        )
        binding.reposRecyclerview.addItemDecoration(dividerItemDecoration)


        /*  newsAdapter.setOnItemClickListener {
                    //do something

                    navigateToNewsDetailsFragment(it)
                }*/
    }


    private fun collectUiStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.trendingReposUiState.collect { uiState ->
                        when (uiState) {
                            is UiState.Loaded -> {
                                binding.reposRecyclerview.visibility = View.VISIBLE
                                toggleShimmerLayout(false)


                                uiState.itemData?.items?.let { items ->
                                    reposAdapter.setList(items.toMutableList())
                                }
                            }

                            is UiState.Error -> {
                                if (uiState.error?.name == ErrorType.InternetError.name) {

                                } else {

                                }
                            }

                            UiState.Loading -> {
                                binding.reposRecyclerview.visibility = View.GONE
                                toggleShimmerLayout(true)
                            }
                        }
                    }
                }

            }
        }
    }

    private fun toggleShimmerLayout(show: Boolean) {
        binding.shimmerView.shimmerViewContainer.isVisible = show
        if (show) binding.shimmerView.shimmerViewContainer.startShimmer() else binding.shimmerView.shimmerViewContainer.stopShimmer()
    }


    companion object {
        @JvmStatic
        fun newInstance() = TrendingReposFragment()
    }
}