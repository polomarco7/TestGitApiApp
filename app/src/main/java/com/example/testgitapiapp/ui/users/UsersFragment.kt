package com.example.testgitapiapp.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.testgitapiapp.R
import com.example.testgitapiapp.databinding.FragmentUsersBinding
import com.example.testgitapiapp.models.UsersList
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val mDisposable = CompositeDisposable()
    private val viewModel: UsersListViewModel by viewModels()
    private val usersListAdapter = UsersListAdapter { user -> onItemClick(user) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usersRecycler.adapter = usersListAdapter

        binding.swipeRefresh.setOnRefreshListener {
            usersListAdapter.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
        lifecycleScope.launch {
            usersListAdapter.addLoadStateListener { loadState ->
                binding.usersRecycler.isVisible = loadState.source.refresh is LoadState.NotLoading
                binding.swipeRefresh.isEnabled = loadState.source.refresh is LoadState.NotLoading
            }
        }

        mDisposable.add(viewModel.getUsersList().subscribe {
            usersListAdapter.submitData(lifecycle, it)
        })
    }

    private fun onItemClick(usersList: UsersList) {
        val bundle = bundleOf("username" to usersList.login)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}