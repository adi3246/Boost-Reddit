package com.boost.reddit.base

import androidx.lifecycle.ViewModel
import com.boost.reddit.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    val statusMessage = SingleLiveEvent<String>()

}