package com.example.nytimes


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.nytimes.presentation.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class NewsHomeTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun newsHomeTest() {
        val constraintLayout = onView(
            allOf(
                withId(R.id.item_container),
                childAtPosition(
                    allOf(
                        withId(R.id.rv_list),
                        childAtPosition(
                            withClassName(`is`("androidx.swiperefreshlayout.widget.SwipeRefreshLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        pressBack()

        val constraintLayout2 = onView(
            allOf(
                withId(R.id.item_container),
                childAtPosition(
                    allOf(
                        withId(R.id.rv_list),
                        childAtPosition(
                            withClassName(`is`("androidx.swiperefreshlayout.widget.SwipeRefreshLayout")),
                            1
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        constraintLayout2.perform(click())

        pressBack()

        val constraintLayout3 = onView(
            allOf(
                withId(R.id.item_container),
                childAtPosition(
                    allOf(
                        withId(R.id.rv_list),
                        childAtPosition(
                            withClassName(`is`("androidx.swiperefreshlayout.widget.SwipeRefreshLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout3.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
