package me.seishin.taskist.ui.tasks.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.seishin.taskist.R
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
class TasksListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TasksListActivity::class.java)

    @Test
    fun tasksListActivityTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.add),
                childAtPosition(
                    allOf(
                        withId(R.id.container),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.taskName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomSheetCreateTask),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Test task"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.done), withText("Done"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomSheetCreateTask),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.title), withText("Test task"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tasksList),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Test task")))

        val constraintLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.tasksList),
                        childAtPosition(
                            withId(R.id.container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.taskName), withText("Test task"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomSheetCreateTask),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.taskName), withText("Test task"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomSheetCreateTask),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("Test task updated"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.taskName), withText("Test task updated"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomSheetCreateTask),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.done), withText("Update"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomSheetCreateTask),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.title), withText("Test task updated"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tasksList),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Test task updated")))

        val constraintLayout2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.tasksList),
                        childAtPosition(
                            withId(R.id.container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout2.perform(click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.delete), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomSheetCreateTask),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        onView(allOf(withId(R.id.title), withText("Test task updated"))).check(doesNotExist())
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
