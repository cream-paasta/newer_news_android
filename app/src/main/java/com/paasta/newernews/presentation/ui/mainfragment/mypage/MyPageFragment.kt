package com.paasta.newernews.presentation.ui.mainfragment.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.paasta.newernews.R
import com.paasta.newernews.databinding.FragmentMyPageBinding
import com.paasta.newernews.presentation.ui.login.LoginActivity
import com.paasta.newernews.presentation.ui.usernews.UserNewsActivity
import com.paasta.newernews.presentation.ui.versioninfo.VersionInfoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment: Fragment() {

    private val myPageViewModel: MyPageViewModel by viewModels()

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)

        binding.tvMyPageName.text = String.format(getString(R.string.my_page_hello), myPageViewModel.getUserName())
        binding.tvMyPageEmail.text = myPageViewModel.getEmail()


        binding.btnLikeNews.setOnClickListener { _ ->
            val intent = Intent(activity, UserNewsActivity::class.java)
            intent.putExtra("isLike", true)
            startActivity(intent)
        }

        binding.btnHateNews.setOnClickListener { _ ->
            val intent = Intent(activity, UserNewsActivity::class.java)
            intent.putExtra("isLike", false)
            startActivity(intent)
        }

        binding.btnVersionInfo.setOnClickListener { _ ->
            startActivity(Intent(activity, VersionInfoActivity::class.java))
        }

        binding.btnLogout.setOnClickListener { _ ->
            myPageViewModel.setLoginFalse()
            startActivity(Intent(activity, LoginActivity::class.java))
            requireActivity().finish()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}