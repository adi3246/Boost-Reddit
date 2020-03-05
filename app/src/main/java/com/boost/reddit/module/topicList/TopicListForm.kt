package com.boost.reddit.module.topicList


import androidx.databinding.Bindable
import com.boost.reddit.BR
import com.boost.reddit.base.ParentBaseObservable
import com.boost.reddit.model.TopicModel
import com.boost.reddit.utils.BindableDelegates

class TopicListForm : ParentBaseObservable() {

    @get:Bindable
    var topicList: ArrayList<TopicModel> by BindableDelegates(ArrayList(), BR.topicList)
}