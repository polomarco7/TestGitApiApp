package com.example.testgitapiapp.ui.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.testgitapiapp.R
import com.example.testgitapiapp.databinding.FragmentUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserDetailsViewModel by viewModels()

    private var userName = String()
    private val mDateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments.let {
            if (it != null) {
                userName = it.getString("username")!!
            }
        }
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserInfo(userName)

        viewModel.contactsData.onEach {user ->
            with(binding) {
                Glide
                    .with(backdrop.context)
                    .load(user.avatar)
                    .into(backdrop)
                nameTxt.text = user.name
                emailTxt.text = getString(R.string.email, user.email)
                companyTxt.text = getString(R.string.company,user.company)
                followingTxt.text = getString(R.string.following,user.following)
                followersTxt.text = getString(R.string.followers,user.followers)
                createdAtTxt.text = getString(R.string.dateat,user.createdAt?.let { mDateFormatter.parse(it) })
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}