package com.boost.reddit.module.topicDetail

import com.boost.reddit.ApplicationClass
import com.boost.reddit.base.BaseViewModel
import com.boost.reddit.model.TopicModel

/**
 * Created by Isa Andi on 18/02/2020.
 * <p>
 * TopicDetailViewModel where the business logic to show topic detail.
 *
 * @author Isa Andi
 * @version 1
 * @see TopicModel
 */
class TopicDetailViewModel: BaseViewModel()  {

    val topicDetailForm = TopicDetailForm()
    var updateTopicList = false

    fun setTopicDetail(topicModel: TopicModel) {
        topicDetailForm.topic = topicModel
    }

    fun saveTopic(topic: String){
        ApplicationClass.topicModel.add(TopicModel(0, topic, 0, 0))
        updateTopicList = true
        statusMessage.value = "Success"
    }

    fun setNewTopic(isNew: Boolean) {
        topicDetailForm.newTopic = isNew
    }
}