package com.boost.reddit

import androidx.test.InstrumentationRegistry
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class TopicListScreenTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.boost.reddit", appContext.packageName)
    }
}