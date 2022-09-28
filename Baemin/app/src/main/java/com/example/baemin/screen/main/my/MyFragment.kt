package com.example.baemin.screen.main.my

import com.example.baemin.databinding.FragmentHomeBinding
import com.example.baemin.databinding.FragmentMyBinding
import com.example.baemin.screen.base.BaseFragment
import com.example.baemin.screen.main.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyFragment: BaseFragment<MyViewModel, FragmentMyBinding>() {

    override val viewModel by viewModel<MyViewModel>()

    override fun getViewBinding(): FragmentMyBinding = FragmentMyBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {
        fun newInstance() = MyFragment()

        const val TAG = "MyFragment"
    }
}