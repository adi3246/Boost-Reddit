package com.boost.reddit.base

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.boost.reddit.BR
import com.boost.reddit.utils.BindableDelegates

open class ParentBaseObservable : BaseObservable() {

    @get:Bindable
    var loadingProgress: Boolean by BindableDelegates(false, BR.loadingProgress)
}