package com.example.baemin.screen.main.home

import androidx.lifecycle.MutableLiveData
import com.example.baemin.screen.base.BaseViewModel

class HomeViewModel: BaseViewModel() {

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)

}