package com.boost.reddit

import com.boost.reddit.module.topicList.TopicListViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TopicListViewModelUnitTest {

    private lateinit var topicListViewModel: TopicListViewModel

    @Before
    fun setup() {
        topicListViewModel = TopicListViewModel()
        topicListViewModel.mockData()
    }

    @Test
    fun `Test data is not null`() {
        assertNotNull(topicListViewModel.topicList)
    }

    @Test
    fun `Test top 20 data`() {
        topicListViewModel.getTop20Topic()
        assertEquals(20, topicListViewModel.topicList.size)
    }
}
