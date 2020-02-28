package com.example.notetakingapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notetakingapp.ui.list.MainActivity
import com.example.notetakingapp.util.ConstantsUtil
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UiTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun fullAppFlowTest() {
        //  main activity
        onView(withId(R.id.add_fab)).run {
            check(matches(isDisplayed()))
            onView(withId(R.id.notes_recyclerview)).run {
                check(matches(isDisplayed()))
                check(matches(hasDescendant(withText(R.string.empty_text))))
            }
            perform(click())
        }

        //  create activity
        onView(withId(R.id.save_fab)).run {
            check(matches(not(isDisplayed())))
            //  title
            onView(withId(R.id.title_edit_text)).run {
                check(matches(withText(ConstantsUtil.EMPTY)))
                perform(typeText(ConstantsUtil.SAMPLE_TITLE))
            }
            //  note
            onView(withId(R.id.note_edit_text)).run {
                check(matches(withText(ConstantsUtil.EMPTY)))
                perform(
                    typeText(ConstantsUtil.SAMPLE_NOTE)
                )
            }
            check(matches(isDisplayed()))
            perform(click())
            //  toast
            onView(withText(R.string.note_saved))
                .inRoot(withDecorView(not(rule.activity.window.decorView)))
                .check(matches(isDisplayed()))
        }

        //  detail activity
        onView(withId(R.id.note_title)).run {
            check(matches(withText(ConstantsUtil.SAMPLE_TITLE)))
        }

        onView(withId(R.id.note_content)).run {
            check(matches(withText(ConstantsUtil.SAMPLE_NOTE)))
        }

        pressBack()

        //  main activity
        onView(withId(R.id.notes_recyclerview)).run {
            check(matches(hasDescendant(withText(containsString(ConstantsUtil.SAMPLE_TITLE)))))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        }

        //  detail activity
        onView(withId(R.id.note_title)).run {
            check(matches(withText(ConstantsUtil.SAMPLE_TITLE)))
        }

        onView(withId(R.id.note_content)).run {
            check(matches(withText(ConstantsUtil.SAMPLE_NOTE)))
        }
    }
}