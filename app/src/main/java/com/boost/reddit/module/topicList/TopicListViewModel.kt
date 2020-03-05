package com.boost.reddit.module.topicList

import com.boost.reddit.ApplicationClass
import com.boost.reddit.base.BaseViewModel
import com.boost.reddit.model.TopicModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by Isa Andi on 18/02/2020.
 * <p>
 * TopicListViewModel where the business logic for fetching list of topics.
 *
 * @author Isa Andi
 * @version 1
 * @see TopicModel
 */
class TopicListViewModel: BaseViewModel()  {

    val topicListForm = TopicListForm()

    fun getTop20Topic() {
        CoroutineScope(Default).launch {
            ApplicationClass.topicModel = ArrayList(ApplicationClass.topicModel.asSequence()
                .sortedByDescending { it.upVote }
                .toMutableList().subList(0,20))

            withContext(Main){
                setData()
            }
        }
    }

    private suspend fun setData(){
        topicListForm.topicList = ApplicationClass.topicModel
    }

    fun mockData(){
        CoroutineScope(Default).launch {
            ApplicationClass.topicModel.add(TopicModel(0, "topic 1", 12, 1))
            ApplicationClass.topicModel.add(TopicModel(1, "topic 2", 133, 1))
            ApplicationClass.topicModel.add(TopicModel(2, "topic 3", 156, 1))
            ApplicationClass.topicModel.add(TopicModel(3, "topic 4", 13, 1))
            ApplicationClass.topicModel.add(TopicModel(4, "topic 5", 16, 1))
            ApplicationClass.topicModel.add(TopicModel(5, "topic 6", 17, 1))
            ApplicationClass.topicModel.add(TopicModel(6, "topic 7", 19, 1))
            ApplicationClass.topicModel.add(TopicModel(7, "topic 8", 112, 1))
            ApplicationClass.topicModel.add(TopicModel(8, "topic 9", 167, 1))
            ApplicationClass.topicModel.add(TopicModel(9, "topic 10", 144, 1))
            ApplicationClass.topicModel.add(TopicModel(10, "topic 11", 177, 1))
            ApplicationClass.topicModel.add(TopicModel(11, "topic 12", 11, 1))
            ApplicationClass.topicModel.add(TopicModel(12, "topic 13", 12, 1))
            ApplicationClass.topicModel.add(TopicModel(13, "topic 14", 157, 1))
            ApplicationClass.topicModel.add(TopicModel(14, "topic 15", 193, 1))
            ApplicationClass.topicModel.add(TopicModel(15, "topic 16", 164, 1))
            ApplicationClass.topicModel.add(TopicModel(16, "topic 17", 189, 1))
            ApplicationClass.topicModel.add(TopicModel(17, "topic 18", 198, 1))
            ApplicationClass.topicModel.add(TopicModel(18, "topic 19", 143, 1))
            ApplicationClass.topicModel.add(TopicModel(19, "topic 20", 134, 1))
            ApplicationClass.topicModel.add(TopicModel(20, "topic 21", 15, 1))
            ApplicationClass.topicModel.add(TopicModel(21,"topic 22", 16, 1))
            ApplicationClass.topicModel.add(TopicModel(22, "topic 23", 17, 1))
            ApplicationClass.topicModel.add(TopicModel(23, "topic 24", 18, 1))
            ApplicationClass.topicModel.add(TopicModel(24, "topic 25", 19, 1))
        }
    }
}