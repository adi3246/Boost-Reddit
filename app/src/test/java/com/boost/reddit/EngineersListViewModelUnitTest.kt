package com.boost.reddit

import com.boost.reddit.module.topicList.TopicListViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class EngineersListViewModelUnitTest {

    private val engineersListViewModel =
        TopicListViewModel()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getEngineer_valid() {

    }
}
