package com.boost.reddit.module.topicList

import androidx.annotation.VisibleForTesting
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

    @VisibleForTesting
    val topicListForm = TopicListForm()

    @VisibleForTesting
    var topicList : ArrayList<TopicModel> = ArrayList()


    @VisibleForTesting
    fun mockData(){
        CoroutineScope(Default).launch {
            topicList.add(TopicModel(0, "topic 1", 12, 1))
            topicList.add(TopicModel(1, "topic 2", 133, 1))
            topicList.add(TopicModel(2, "topic 3", 156, 1))
            topicList.add(TopicModel(3, "topic 4", 13, 1))
            topicList.add(TopicModel(4, "topic 5", 16, 1))
            topicList.add(TopicModel(5, "topic 6", 17, 1))
            topicList.add(TopicModel(6, "topic 7", 19, 1))
            topicList.add(TopicModel(7, "topic 8", 112, 1))
            topicList.add(TopicModel(8, "topic 9", 167, 1))
            topicList.add(TopicModel(9, "topic 10", 144, 1))
            topicList.add(TopicModel(10, "topic 11", 177, 1))
            topicList.add(TopicModel(11, "topic 12", 11, 1))
            topicList.add(TopicModel(12, "topic 13", 12, 1))
            topicList.add(TopicModel(13, "topic 14", 157, 1))
            topicList.add(TopicModel(14, "topic 15", 193, 1))
            topicList.add(TopicModel(15, "topic 16", 164, 1))
            topicList.add(TopicModel(16, "topic 17", 189, 1))
            topicList.add(TopicModel(17, "topic 18", 198, 1))
            topicList.add(TopicModel(18, "topic 19", 143, 1))
            topicList.add(TopicModel(19, "topic 20", 134, 1))
            topicList.add(TopicModel(20, "topic 21", 15, 1))
            topicList.add(TopicModel(21,"topic 22", 16, 1))
            topicList.add(TopicModel(22, "topic 23", 17, 1))
            topicList.add(TopicModel(23, "topic 24", 18, 1))
            topicList.add(TopicModel(24, "topic 25", 19, 1))

            getTop20Topic()

            withContext(Main){
                setData()
            }
        }
    }

    fun addNewTopicList(topicModel: TopicModel){
        CoroutineScope(Default).launch {
            topicList.add(topicModel)

            getTop20Topic()

            withContext(Main){
                setData()
            }
        }
    }

    private suspend fun getTop20Topic(){
        topicList = ArrayList(topicList.asSequence()
            .sortedByDescending { it.upVote }
            .toMutableList().subList(0,20))
    }

    private suspend fun setData(){
        topicListForm.topicList = topicList
    }
}