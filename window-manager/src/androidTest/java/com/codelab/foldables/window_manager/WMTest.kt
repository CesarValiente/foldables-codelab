package com.codelab.foldables.window_manager

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.window.layout.FoldingFeature.Orientation.Companion.HORIZONTAL
import androidx.window.layout.FoldingFeature.Orientation.Companion.VERTICAL
import androidx.window.layout.FoldingFeature.State.Companion.FLAT
import androidx.window.testing.layout.FoldingFeature
import androidx.window.testing.layout.TestWindowLayoutInfo
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class WMTest {

    private val activityRule = ActivityScenarioRule(CodelabActivity::class.java)
    private val publisherRule = WindowLayoutInfoPublisherRule()

    @get:Rule
    val testRule: TestRule

    init {
        testRule = RuleChain.outerRule(publisherRule).around(activityRule)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testText_is_left_of_Vertical_FoldingFeature() {
        activityRule.scenario.onActivity { activity ->
            val hinge = FoldingFeature(
                activity = activity,
                state = FLAT,
                orientation = VERTICAL,
                size = 2
            )

            val expected = TestWindowLayoutInfo(listOf(hinge))
            publisherRule.overrideWindowLayoutInfo(expected)
        }

        onView(withId(R.id.layout_change)).check(
            isCompletelyLeftOf(withId(R.id.device_feature))
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testText_is_below_FoldingFeature() {
        activityRule.scenario.onActivity { activity ->
            val hinge = FoldingFeature(
                activity = activity,
                state = FLAT,
                orientation = HORIZONTAL,
                size = 2
            )

            val expected = TestWindowLayoutInfo(listOf(hinge))
            publisherRule.overrideWindowLayoutInfo(expected)
        }

        onView(withId(R.id.layout_change)).check(
            isCompletelyBelow(withId(R.id.device_feature))
        )
    }
}