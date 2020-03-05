package com.boost.reddit.module.topicDetail

import androidx.databinding.Bindable
import com.boost.reddit.BR
import com.boost.reddit.base.ParentBaseObservable
import com.boost.reddit.model.TopicModel
import com.boost.reddit.utils.BindableDelegates

class TopicDetailForm : ParentBaseObservable() {
    @get:Bindable
    var topic: TopicModel by BindableDelegates(TopicModel(), BR.topic)

    @get:Bindable
    var newTopic: Boolean by BindableDelegates(true, BR.newTopic)

}