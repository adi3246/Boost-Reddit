package com.boost.reddit

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage.RESUMED
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Useful test methods common to all activities
 */
object TestUtils {

    /**
     * Gets an Activity in the RESUMED stage.
     *
     *
     * This method should never be called from the Main thread. In certain situations there might
     * be more than one Activities in RESUMED stage, but only one is returned.
     * See [ActivityLifecycleMonitor].
     */
    // The array is just to wrap the Activity and be able to access it from the Runnable.
    val currentActivity: Activity
        @Throws(IllegalStateException::class)
        get() {
            val resumedActivity = arrayOfNulls<Activity>(1)

            getInstrumentation().runOnMainSync(Runnable {
                val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(RESUMED)
                if (resumedActivities.iterator().hasNext()) {
                    resumedActivity[0] = resumedActivities.iterator().next() as Activity
                } else {
                    throw IllegalStateException("No Activity in stage RESUMED")
                }
            })
            return resumedActivity[0]!!
        }

    private fun rotateToLandscape(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    private fun rotateToPortrait(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun rotateOrientation(activity: Activity) {
        val currentOrientation = activity.resources.configuration.orientation

        when (currentOrientation) {
            Configuration.ORIENTATION_LANDSCAPE -> rotateToPortrait(activity)
            Configuration.ORIENTATION_PORTRAIT -> rotateToLandscape(activity)
            else -> rotateToLandscape(activity)
        }
    }

    /**
     * Returns the content description for the navigation button view in the toolbar.
     */
    fun getToolbarNavigationContentDescription(
        @NonNull activity: Activity, @IdRes toolbar1: Int
    ): String {
        val toolbar = activity.findViewById<View>(toolbar1) as Toolbar?
        return toolbar?.navigationContentDescription?.toString() ?: throw RuntimeException("No toolbar found.")
    }

    /**
     * Matches the toolbar title with a specific string resource.
     *
     * @param resourceId the ID of the string resource to match
     */
    fun withToolbarTitle(resourceId: Int): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title from resource id: ")
                description.appendValue(resourceId)
            }

            override fun matchesSafely(toolbar: Toolbar): Boolean {
                var expectedText: CharSequence = ""
                try {
                    expectedText = toolbar.resources.getString(resourceId)
                } catch (ignored: Resources.NotFoundException) {
                    /* view could be from a context unaware of the resource id. */
                }

                val actualText = toolbar.title
                return expectedText == actualText
            }
        }
    }
}
